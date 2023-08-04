package com.service.orderservice.service;

import com.service.orderservice.dto.OrderLineItemsDto;
import com.service.orderservice.dto.OrderRequest;
import com.service.orderservice.models.Order;
import com.service.orderservice.models.OrderLineItems;
import com.service.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {


    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest) {

        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setId(orderLineItemsDto.getId());

        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        orderLineItems.setPrice(orderLineItemsDto.getPrice());

        return orderLineItems;
    }
}
