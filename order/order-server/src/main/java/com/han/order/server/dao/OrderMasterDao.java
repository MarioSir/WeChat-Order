package com.han.order.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.han.order.server.entity.OrderMaster;

public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {
}
