package com.han.product.server.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ProductOutVO {
	@JsonProperty("name")
	private String categoryName;
	@JsonProperty("type")
	private Integer categoryType;
	@JsonProperty("foods")
	List<ProductInfoVO> ProductInfoVOList;
}
