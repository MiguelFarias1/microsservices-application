package com.service.orderservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_order_line_items")
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String skuCode;

    private BigDecimal price;

    private Integer quantity;
}
