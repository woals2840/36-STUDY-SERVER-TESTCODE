package sopt.study.testcode.jaemin.spring.api.controller.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sopt.study.testcode.jaemin.spring.api.ApiResponse;
import sopt.study.testcode.jaemin.spring.api.controller.product.request.ProductCreateRequest;
import sopt.study.testcode.jaemin.spring.api.service.product.ProductService;
import sopt.study.testcode.jaemin.spring.api.service.product.response.ProductResponse;

@RestController
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@PostMapping("/api/v1/products/new")
	public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreateRequest request) {
		return ApiResponse.of(HttpStatus.OK, productService.createProduct(request));
	}

	@GetMapping("/api/v1/products/selling")
	public ApiResponse<List<ProductResponse>> getSellingProducts() {
		return ApiResponse.ok(productService.getSellingProducts());
	}
}
