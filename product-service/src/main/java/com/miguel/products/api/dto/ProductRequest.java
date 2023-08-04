package com.miguel.products.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String description;

    @NotNull
    private BigDecimal price;
}
