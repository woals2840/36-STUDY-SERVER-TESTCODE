package sopt.study.testcode.jaemin.spring.domain.product;

import static sopt.study.testcode.jaemin.spring.domain.product.ProductSellingStatus.*;
import static sopt.study.testcode.jaemin.spring.domain.product.ProductType.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import sopt.study.testcode.jaemin.CafekioskApplication;
import sopt.study.testcode.jaemin.spring.api.controller.product.request.ProductCreateRequest;
import sopt.study.testcode.jaemin.spring.api.service.product.ProductService;
import sopt.study.testcode.jaemin.spring.api.service.product.response.ProductResponse;
import sopt.study.testcode.jaemin.spring.domain.product.Product;
import sopt.study.testcode.jaemin.spring.domain.product.ProductRepository;
import sopt.study.testcode.jaemin.spring.domain.product.ProductSellingStatus;
import sopt.study.testcode.jaemin.spring.domain.product.ProductType;

@ActiveProfiles("test")
@SpringBootTest(classes = CafekioskApplication.class)
public class ProductServiceTest {
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@AfterEach
	void tearDown() {
		productRepository.deleteAllInBatch();
	}

	@DisplayName("신규상품을 등록한다. 상품 번호는 가장 최근 상품의 상품번호에서 1 증가한 값이다.")
	@Test
	void createProduct() {
	    //given
		Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
		Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
		Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
		productRepository.saveAll(List.of(product1, product2, product3));

		ProductCreateRequest request = ProductCreateRequest.builder()
			.type(HANDMADE)
			.sellingStatus(SELLING)
			.name("카푸치노")
			.price(3000)
			.build();

		//when
		ProductResponse response = productService.createProduct(request);

	    //then
		assertThat(response)
			.extracting("productNumber", "type", "sellingStatus", "name", "price")
			.contains("004", HANDMADE, SELLING, "카푸치노", 3000);
	 }

	@DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다")
	@Test
	void createProductWHenProductIsEmpty() {
		//given
		ProductCreateRequest request = ProductCreateRequest.builder()
			.type(HANDMADE)
			.sellingStatus(SELLING)
			.name("카푸치노")
			.price(3000)
			.build();

		//when
		ProductResponse response = productService.createProduct(request);

		//then
		assertThat(response)
			.extracting("productNumber", "type", "sellingStatus", "name", "price")
			.contains("001", HANDMADE, SELLING, "카푸치노", 3000);
	}

	private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
		return Product.builder()
			.productNumber(productNumber)
			.type(type)
			.sellingStatus(sellingStatus)
			.name(name)
			.price(price)
			.build();
	}
}
