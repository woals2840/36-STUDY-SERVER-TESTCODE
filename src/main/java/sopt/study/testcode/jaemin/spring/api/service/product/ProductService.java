package sopt.study.testcode.jaemin.spring.api.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sopt.study.testcode.jaemin.spring.api.controller.product.request.ProductCreateRequest;
import sopt.study.testcode.jaemin.spring.api.service.product.response.ProductResponse;
import sopt.study.testcode.jaemin.spring.domain.product.Product;
import sopt.study.testcode.jaemin.spring.domain.product.ProductRepository;
import sopt.study.testcode.jaemin.spring.domain.product.ProductSellingStatus;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	//동시성 이슈 -> 새로운 정책(UUID), unique한 값 걸도록~
	@Transactional
	public ProductResponse createProduct(ProductCreateRequest request) {
		String nextProductNumber = createNextProductNumber();

		Product product = request.toEntity(nextProductNumber);
		Product savedProduct = productRepository.save(product);

		return ProductResponse.of(savedProduct);
	}

	public List<ProductResponse> getSellingProducts() {
		List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

		return products.stream()
			.map(ProductResponse::of)
			.collect(Collectors.toList());
	}

	private String createNextProductNumber() {
		String latestProductNumber = productRepository.findLatestProductNumber();
		if (latestProductNumber == null) {
			return "001";
		}

		int latestProductNumberInt = Integer.parseInt(latestProductNumber);
		int nextProductNumberInt = latestProductNumberInt + 1;

		// 9 -> 009
		return String.format("%03d", nextProductNumberInt);
	}

}
