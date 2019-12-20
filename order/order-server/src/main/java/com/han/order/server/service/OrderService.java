package com.han.order.server.service;

import com.han.order.common.dto.OrderDTO;

public interface OrderService {
    /**
     * 创建订单
     * 1. 参数检验
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     */
    OrderDTO create(OrderDTO orderDTO);
}
