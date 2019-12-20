package com.han.order.common.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 订单明细快照
 */
@Data
public class OrderDetailDTO {

    private String detailId;

    /** 订单id. */
    private String orderId;

    /** 商品id. */
    private String productId;

    /** 商品名称. */
    private String productName;

    /** 商品单价. */
    private BigDecimal productPrice;

    /** 商品数量. */
    private Integer productQuantity;

    /** 商品小图. */
    private String productIcon;
}
