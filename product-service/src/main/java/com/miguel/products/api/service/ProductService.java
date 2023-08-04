package com.miguel.products.api.service;

import com.miguel.products.api.dto.ProductRequest;
import com.miguel.products.api.dto.ProductResponse;
import com.miguel.products.api.model.Product;
import com.miguel.products.api.repositoriy.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        log.info(String.format("Product %s is saved", product.getId()));
    }

    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
