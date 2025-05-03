package com.order_service.services.kafka.order;

import com.order_service.dtos.order.OrderDto;

public interface KafkaOrderProducerService {

    void sendOrderPlacedEvent(OrderDto orderDto);
}
