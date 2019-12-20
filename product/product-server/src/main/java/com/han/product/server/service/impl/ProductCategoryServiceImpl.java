package com.han.product.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.han.product.server.dao.ProductCategoryDao;
import com.han.product.server.entity.ProductCategory;
import com.han.product.server.service.CategoryService;

@Service
public class ProductCategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypeList);
    }
}
