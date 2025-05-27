package sopt.study.testcode.jaemin.spring.api.controller.product;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sopt.study.testcode.jaemin.spring.api.controller.product.request.ProductCreateRequest;
import sopt.study.testcode.jaemin.spring.api.service.product.ProductService;
import sopt.study.testcode.jaemin.spring.api.service.product.response.ProductResponse;
import sopt.study.testcode.jaemin.spring.domain.product.ProductSellingStatus;
import sopt.study.testcode.jaemin.spring.domain.product.ProductType;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ProductService productService;

	@DisplayName("신규 상품을 등록한다")
	@Test
	void createProduct() throws Exception {
	    //given
		ProductCreateRequest request = ProductCreateRequest.builder()
			.type(ProductType.HANDMADE)
			.sellingStatus(ProductSellingStatus.SELLING)
			.name("아메리카노")
			.price(4000)
			.build();

		//when 		//then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/new")
			.content(objectMapper.writeValueAsString(request))
			.contentType(MediaType.APPLICATION_JSON))

			.andExpect(status().isOk());
	 }

	@DisplayName("신규 상품을 등록할 때 상품 타입은 필수값이다")
	@Test
	void createProductWithoutType() throws Exception {
		//given
		ProductCreateRequest request = ProductCreateRequest.builder()
			.sellingStatus(ProductSellingStatus.SELLING)
			.name("아메리카노")
			.price(4000)
			.build();

		//when 		//then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/new")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.code").value("400"))
			.andExpect(jsonPath("$.status").value("BAD_REQUEST"))
			.andExpect(jsonPath("$.message").value("상품 타입은 필수입니다."))
			.andExpect(jsonPath("$.data").isEmpty())
		;
	}

	@DisplayName("판매상품을 조회한다")
	@Test
	void getSellingProducts() throws Exception {
		//given
		List<ProductResponse> result = List.of();

		when(productService.getSellingProducts()).thenReturn(result);

		//when 		//then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/selling")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.code").value("200"))
			.andExpect(jsonPath("$.status").value("OK"))
			.andExpect(jsonPath("$.message").value("OK"))
			.andExpect(jsonPath("$.data").isArray())
		;
	}
}
