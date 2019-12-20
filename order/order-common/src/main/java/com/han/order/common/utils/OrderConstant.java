package com.han.order.common.utils;

import com.han.result.CodeMsg;

public class OrderConstant{

    //订单模块 5004XX
    public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500400, "订单不存在");
    public static CodeMsg CAT_IS_EMPTY= new CodeMsg(500401, "购物车信息为空");
}
