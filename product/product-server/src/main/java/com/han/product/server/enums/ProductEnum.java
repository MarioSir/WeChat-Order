package com.han.product.server.enums;

import lombok.Data;
import lombok.Getter;

/**
 * 商品状态
 */
@Getter
public enum ProductEnum {
    UP(1,"在架"),DOWN(0,"下架");

    private int code;
    private String name;

    ProductEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
