package com.order_service.services.kafka.order;

import com.order_service.dtos.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaOrderProducerServiceImpl implements KafkaOrderProducerService{

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaOrderProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${app.kafka.topics.order-placed}")
    private String orderPlacedTopic;

    @Override
    public void sendOrderPlacedEvent(OrderDto orderDto) {

        kafkaTemplate.send(orderPlacedTopic, orderDto.getId(), orderDto.toString());
        log.info("order placed and sent to payment-service on {} topic", orderPlacedTopic);

    }
}
