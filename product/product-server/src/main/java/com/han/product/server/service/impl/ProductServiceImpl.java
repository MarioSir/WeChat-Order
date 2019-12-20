package com.han.product.server.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.han.product.common.dto.DecreaseStockInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.han.product.server.dao.ProductDao;
import com.han.product.server.entity.Product;
import com.han.product.server.enums.ProductEnum;
import com.han.product.server.exception.ProductException;
import com.han.product.server.service.ProductService;
import com.han.product.server.utils.ProductConstant;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findUpAll() {
        return productDao.findByProductStatus(ProductEnum.UP.getCode());
    }

    @Override
    public List<Product> findByProductIdIn(List<String> productIds) {
        return productDao.findByProductIdIn(productIds);
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputs) {
        if(!CollectionUtils.isEmpty(decreaseStockInputs)){
        	decreaseStockInputs.stream().forEach(cartDTO -> {
                Optional<Product> productOptional= productDao.findById(cartDTO.getProductId());
                if(!productOptional.isPresent()){//判断商品不存在
                    throw new ProductException(ProductConstant.PRODUCT_NOT_EXIST);
                }
                Product product = productOptional.get();
                int result = product.getProductStock() - cartDTO.getProductQuantity();
                if(result<0){//判断商品库存
                    throw new ProductException(ProductConstant.PRODUCT_IS_EMPTY);
                }
                product.setProductStock(result);
                productDao.save(product);
            });
        }
    }
}
