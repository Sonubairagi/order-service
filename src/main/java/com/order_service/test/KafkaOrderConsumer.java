package com.order_service.test;

import com.order_service.dtos.order.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaOrderConsumer {

//    @KafkaListener(topics = "${app.kafka.topics.order-placed}", groupId = "${spring.kafka.consumer.group-id}")
//    @KafkaListener(topics = "order-placed", groupId = "order-place-group")
//    public void receiveOrderPlacedEvent(String jsonOrderDto) {
//
//        log.info("received order-placed event: {}", jsonOrderDto);
//    }
}
