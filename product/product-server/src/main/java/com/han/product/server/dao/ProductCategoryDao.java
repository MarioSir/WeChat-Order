package com.han.product.server.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.han.product.server.entity.ProductCategory;



public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
