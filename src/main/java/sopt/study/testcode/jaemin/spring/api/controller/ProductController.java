package sopt.study.testcode.jaemin.spring.api.controller;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import sopt.study.testcode.jaemin.spring.api.service.product.ProductService;
import sopt.study.testcode.jaemin.spring.api.service.product.response.ProductResponse;

@Repository
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping("/api/v1/products/selling")
	public List<ProductResponse> getSellingProducts(){
		return productService.getSellingProducts();
	}
}
