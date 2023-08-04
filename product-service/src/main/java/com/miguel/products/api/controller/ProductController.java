package com.miguel.products.api.controller;

import com.miguel.products.api.dto.ProductRequest;
import com.miguel.products.api.dto.ProductResponse;
import com.miguel.products.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creteProduct(@RequestBody ProductRequest productRequest) {

        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {

        return productService.getAllProducts();
    }
}
