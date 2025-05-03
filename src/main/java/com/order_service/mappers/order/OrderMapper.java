package com.order_service.mappers.order;


import com.order_service.dtos.order.OrderDto;
import com.order_service.entities.order.Order;

public class OrderMapper {

    public static OrderDto mapOrderToDto(Order order) {

        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setDiscountAmount(order.getDiscountAmount());
        orderDto.setFinalAmount(order.getFinalAmount());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setPaymentStatus(order.getPaymentStatus());
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setModifiedAt(order.getModifiedAt());
        orderDto.setGift(order.isGift());
        orderDto.setProductId(order.getProductId());
        orderDto.setProductName(order.getProductName());

        return orderDto;
    }

    public static Order mapDtoToOrder(OrderDto orderDto) {

        Order order = new Order();
        order.setId(orderDto.getId());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setDiscountAmount(orderDto.getDiscountAmount());
        order.setFinalAmount(orderDto.getFinalAmount());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setPaymentStatus(orderDto.getPaymentStatus());
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setModifiedAt(orderDto.getModifiedAt());
        order.setGift(orderDto.isGift());
        order.setProductId(orderDto.getProductId());
        order.setProductName(orderDto.getProductName());

        return order;
    }
}
