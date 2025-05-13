package sample.cafekiosk.jaemin.unit.product;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import sample.cafekiosk.spring.CafekioskApplication;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@ActiveProfiles("test")
@SpringBootTest(classes = CafekioskApplication.class)
//@DataJpaTest
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@DisplayName("원하는 판매 상태를 가진 상품들을 조회한다")
	@Test
	void findAllBySellingStatusIn() {
	    //given
		Product product1 = Product.builder()
			.productNumber("001")
			.type(ProductType.HANDMADE)
			.sellingStatus(ProductSellingStatus.SELLING)
			.name("아메리카노")
			.price(4000)
			.build();

		Product product2 = Product.builder()
			.productNumber("002")
			.type(ProductType.HANDMADE)
			.sellingStatus(ProductSellingStatus.HOLD)
			.name("카페라떼")
			.price(4500)
			.build();

		Product product3 = Product.builder()
			.productNumber("003")
			.type(ProductType.HANDMADE)
			.sellingStatus(ProductSellingStatus.STOP_SELLING)
			.name("팥빙수")
			.price(7000)
			.build();

		productRepository.saveAll(List.of(product1, product2, product3));

	    //when
		List<Product> products = productRepository.findAllBySellingStatusIn(List.of(ProductSellingStatus.SELLING, ProductSellingStatus.HOLD));

	    //then
		assertThat(products).hasSize(2)
			.extracting("productNumber", "name", "sellingStatus")
			.containsExactlyInAnyOrder(
				tuple("001", "아메리카노", ProductSellingStatus.SELLING),
				tuple("002", "카페라떼", ProductSellingStatus.HOLD)
			);
	 }

}
