package com.han.product.server.utils;

import com.han.result.CodeMsg;

public class ProductConstant {

    //订单模块 4004XX
    public static CodeMsg PRODUCT_NOT_EXIST = new CodeMsg(400400, "商品不存在");
    public static CodeMsg PRODUCT_IS_EMPTY= new CodeMsg(400401, "商品库存不足");
}
