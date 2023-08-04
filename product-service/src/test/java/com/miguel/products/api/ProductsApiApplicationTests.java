package com.miguel.products.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguel.products.api.dto.ProductRequest;
import com.miguel.products.api.repositoriy.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductsApiApplicationTests {

	@Container
	static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);

		dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);

		dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);

	}

	@Test
	void shouldCreateProduct() throws Exception {

		ProductRequest productRequest = getProductRequest();

		String productRequestString = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());

        Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private ProductRequest getProductRequest() {

		return ProductRequest.builder()
				.name("Iphone 14")
				.description("A14 Bionic")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

}