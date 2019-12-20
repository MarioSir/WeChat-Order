package com.han.order.server.converter;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.han.order.common.dto.OrderDTO;
import com.han.order.common.dto.OrderDetailDTO;
import com.han.order.common.form.OrderForm;
import com.han.order.server.exception.OrderException;

import com.han.result.SystemConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单表单提交数据转换订单数据对象
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        List<OrderDetailDTO> orderDetailList =null;
        JSONObject jsonObject=null;
        OrderDTO orderDTO=null;

        orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        try {
            jsonObject=new JSONObject();
            orderDetailList=JSONObject.parseArray(orderForm.getItems(),OrderDetailDTO.class);
        } catch (Exception e) {
            log.error("【订单表单提交数据转换订单数据对象】错误, string={}", orderForm.getItems());
            throw new OrderException(SystemConstant.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
