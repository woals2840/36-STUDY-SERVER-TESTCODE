package sopt.study.testcode.jaemin.spring.api.service.product.response;

import lombok.Builder;
import lombok.Getter;
import sopt.study.testcode.jaemin.spring.domain.product.Product;
import sopt.study.testcode.jaemin.spring.domain.product.ProductSellingStatus;
import sopt.study.testcode.jaemin.spring.domain.product.ProductType;

@Getter
public class ProductResponse {
	private Long id;
	private String productNumber;
	private ProductType productType;
	private ProductSellingStatus productSellingStatus;
	private String name;
	private int price;

	@Builder
	private ProductResponse(Long id, String productNumber, ProductType productType,
		ProductSellingStatus productSellingStatus,
		String name, int price) {
		this.id = id;
		this.productNumber = productNumber;
		this.productType = productType;
		this.productSellingStatus = productSellingStatus;
		this.name = name;
		this.price = price;
	}

	public static ProductResponse of(Product product) {
		return ProductResponse.builder()
			.id(product.getId())
			.productNumber(product.getProductNumber())
			.productType(product.getType())
			.productSellingStatus(product.getSellingStatus())
			.name(product.getName())
			.price(product.getPrice())
			.build();
	}
}
