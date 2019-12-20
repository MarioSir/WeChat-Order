package com.han.product.server.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.han.product.common.dto.DecreaseStockInput;
import com.han.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.han.product.server.entity.Product;
import com.han.product.server.entity.ProductCategory;
import com.han.product.server.service.CategoryService;
import com.han.product.server.service.ProductService;
import com.han.product.server.vo.ProductInfoVO;
import com.han.product.server.vo.ProductOutVO;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("/list")
    public Result<List<ProductOutVO>> list() {
        //查询所有在架的商品
        List<Product> upProductAll = productService.findUpAll();
        //查询商品分类信息并且排重
        List<Integer> categoryTypeList = upProductAll.stream().map(Product::getCategoryType).distinct().collect(Collectors.toList());
        //获取类目type列表
        List<ProductCategory> productCategorys = categoryService.findByCategoryTypeIn(categoryTypeList);
        //组合数据
        List<ProductOutVO> productVOList= productCategorys.stream().map(productCategory -> {
            ProductOutVO productVO = new ProductOutVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOS = upProductAll.stream().filter(product -> product.getCategoryType() == productCategory.getCategoryType()).map(product -> {
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(product, productInfoVO);
                return productInfoVO;
            }).collect(Collectors.toList());
            productVO.setProductInfoVOList(productInfoVOS);
            return productVO;
        }).collect(Collectors.toList());
        return Result.SUCCESS(productVOList);
    }

    /**
     * 根据一组商品id查询商品信息(给创建订单接口使用)
     * @param productIds
     * @return
     */
    @PostMapping("/productListForCreateOrder")
    public List<Product> productListForCreateOrder(@RequestBody List<String> productIds) {
        return productService.findByProductIdIn(productIds);
    }

    /**
     * 扣除商品库存
     * @param decreaseStockInputs
     * @return
     */
    @PostMapping("/decreaseStock")
    public Result decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputs) {
        productService.decreaseStock(decreaseStockInputs);
        return Result.SUCCESS();
    }
}
