package com.service.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {

    private UUID id;

    private String skuCode;

    private BigDecimal price;

    private Integer quantity;
}
