package sopt.study.testcode.jaemin.spring.api.controller.order;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sopt.study.testcode.jaemin.spring.api.ApiResponse;
import sopt.study.testcode.jaemin.spring.api.controller.order.request.OrderCreateRequest;
import sopt.study.testcode.jaemin.spring.api.service.order.OrderService;
import sopt.study.testcode.jaemin.spring.api.service.order.reponse.OrderResponse;

@RequiredArgsConstructor
@RestController
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/api/v1/orders/new")
	public ApiResponse<OrderResponse> createOrder(@RequestBody OrderCreateRequest request) {
		LocalDateTime registeredDateTime = LocalDateTime.now();
		return ApiResponse.ok(orderService.createOrder(request, registeredDateTime));
	}

}
