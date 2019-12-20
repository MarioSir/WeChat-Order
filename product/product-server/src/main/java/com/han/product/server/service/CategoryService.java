package com.han.product.server.service;

import java.util.List;

import com.han.product.server.entity.ProductCategory;

public interface CategoryService {
    /**
     * 根据类目编号查询分类
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
