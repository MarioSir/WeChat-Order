package com.han.product.client;

import java.util.List;

import com.han.product.common.dto.DecreaseStockInput;
import com.han.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.han.product.common.dto.ProductInfoOutput;

@FeignClient(name="product")
public interface ProductClient {

    @GetMapping("getProductMsg")
    String getProductMsg();

    @PostMapping("/product/productListForCreateOrder")
    public List<ProductInfoOutput> productListForCreateOrder(@RequestBody List<String> productIds);

    @PostMapping("/product/decreaseStock")
    public Result decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOS);
}
