package com.han.order.server.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.han.product.common.dto.DecreaseStockInput;
import com.han.result.CodeMsg;
import com.han.result.Result;
import com.han.result.SystemConstant;
import com.han.uuidUtils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.han.order.common.dto.OrderDTO;
import com.han.order.common.dto.OrderDetailDTO;
import com.han.order.common.enums.OrderStatusEnum;
import com.han.order.common.enums.PayStatusEnum;
import com.han.order.common.utils.OrderConstant;
import com.han.order.server.dao.OrderDetailDao;
import com.han.order.server.dao.OrderMasterDao;
import com.han.order.server.entity.OrderDetail;
import com.han.order.server.entity.OrderMaster;
import com.han.order.server.exception.OrderException;
import com.han.order.server.service.OrderService;
import com.han.product.client.ProductClient;
import com.han.product.common.dto.ProductInfoOutput;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductClient productClient;
    /**
     * 创建订单
     * 1. 查询商品信息(调用商品服务)
     * 2. 计算总价
     * 3. 扣库存(调用商品服务)
     * 4. 订单入库
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        //查询商品信息(调用商品服务)
        List<String> productIds = orderDTO.getOrderDetailList().stream().map(OrderDetailDTO::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> products = productClient.productListForCreateOrder(productIds);
        if(CollectionUtils.isEmpty(products)){
             throw new OrderException(OrderConstant.CAT_IS_EMPTY);
        }
        //计算总价
        AtomicReference<BigDecimal> orderAmount= new AtomicReference<>(BigDecimal.ZERO);
        List<DecreaseStockInput> decreaseStockInputs = orderDTO.getOrderDetailList().stream().map(orderDetailDTO -> {
            //根据商品价格和购物车总价计算订单总价
            products.stream().filter(product -> product.getProductId().equals(orderDetailDTO.getProductId())).forEach(product -> {
                orderAmount.set(product.getProductPrice().multiply(new BigDecimal(orderDetailDTO.getProductQuantity())).add(orderAmount.get()));
                BeanUtils.copyProperties(product, orderDetailDTO);
                orderDetailDTO.setOrderId(orderId);
                orderDetailDTO.setDetailId(KeyUtil.genUniqueKey());
                OrderDetail orderDetail=new OrderDetail();
                BeanUtils.copyProperties(orderDetailDTO, orderDetail);
                orderDetailDao.save(orderDetail);
            });
            return new DecreaseStockInput(orderDetailDTO.getProductId(), orderDetailDTO.getProductQuantity());
        }).collect(Collectors.toList());
        //扣库存(调用商品服务)
        Result result = productClient.decreaseStock(decreaseStockInputs);
        if(result.getCode()!= SystemConstant.SUCCESS.getCode()){
            throw new OrderException(new CodeMsg(result.getCode(),result.getMsg()));
        }
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount.get());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);
        return orderDTO;
    }
}
