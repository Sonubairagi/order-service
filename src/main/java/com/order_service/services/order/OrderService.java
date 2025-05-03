package com.order_service.services.order;

import com.order_service.dtos.order.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto saveOrder(OrderDto orderDto);

    OrderDto getOrder(String id);

    List<OrderDto> getAllOrders();

    OrderDto updateOrder(OrderDto orderDto);

    boolean deleteOrder(String id);
}
