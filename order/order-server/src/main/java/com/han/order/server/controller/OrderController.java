package com.han.order.server.controller;

import javax.validation.Valid;

import com.han.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.han.order.common.dto.OrderDTO;
import com.han.order.common.form.OrderForm;
import com.han.order.common.utils.OrderConstant;
import com.han.order.server.converter.OrderForm2OrderDTOConverter;
import com.han.order.server.exception.OrderException;
import com.han.order.server.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param orderForm
     * @return
     */
    @PostMapping("/create")
    public Result create(@Valid OrderForm orderForm){
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){//判断购物车信息是否为空
            throw new OrderException(OrderConstant.CAT_IS_EMPTY);
        }
        OrderDTO order = orderService.create(orderDTO);
        String orderId = JSON.toJSONString(order, new SimplePropertyPreFilter(OrderDTO.class, "orderId"));
        return Result.SUCCESS(JSON.parse(orderId));
    }
}
