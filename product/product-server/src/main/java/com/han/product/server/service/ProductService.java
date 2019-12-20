package com.han.product.server.service;

import java.util.List;

import com.han.product.common.dto.DecreaseStockInput;
import com.han.product.server.entity.Product;

public interface ProductService {
    /**
     * 查询所有在架商品
     * @return
     */
    public List<Product> findUpAll();
    /**
     * 根据一组商品id查询商品信息
     * @param productIds
     * @return
     */
    List<Product> findByProductIdIn(List<String> productIds);

    /**
     * 扣除库存
     * @param decreaseStockInputs
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputs);
}
