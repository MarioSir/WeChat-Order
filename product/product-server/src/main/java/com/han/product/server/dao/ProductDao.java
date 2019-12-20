package com.han.product.server.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.han.product.server.entity.Product;

public interface ProductDao extends JpaRepository<Product, String> {
    /**
     * 根据状态查询商品信息
     * @param productStatus
     * @return
     */
    List<Product> findByProductStatus(Integer productStatus);

    /**
     * 根据一组商品id查询商品信息
     * @param productIds
     * @return
     */
    List<Product> findByProductIdIn(List<String> productIds);

}
