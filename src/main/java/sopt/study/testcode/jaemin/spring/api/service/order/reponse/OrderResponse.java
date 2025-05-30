package sopt.study.testcode.jaemin.spring.api.service.order.reponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import sopt.study.testcode.jaemin.spring.api.service.product.response.ProductResponse;
import sopt.study.testcode.jaemin.spring.domain.order.Order;


@Getter
public class OrderResponse {

	private Long id;
	private int totalPrice;
	private LocalDateTime registeredDateTime;
	private List<ProductResponse> products;

	@Builder
	private OrderResponse(Long id, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponse> products) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.registeredDateTime = registeredDateTime;
		this.products = products;
	}

	public static OrderResponse of(Order order) {
		return OrderResponse.builder()
			.id(order.getId())
			.totalPrice(order.getTotalPrice())
			.registeredDateTime(order.getRegisteredDateTime())
			.products(order.getOrderProducts().stream()
				.map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
				.collect(Collectors.toList())
			)
			.build();
	}

}
