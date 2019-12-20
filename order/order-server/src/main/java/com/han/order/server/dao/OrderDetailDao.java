package com.han.order.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.han.order.server.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {
}
