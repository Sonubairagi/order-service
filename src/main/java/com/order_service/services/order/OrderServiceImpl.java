package com.order_service.services.order;

import com.order_service.dtos.order.OrderDto;
import com.order_service.dtos.product.ProductDto;
import com.order_service.entities.order.Order;
import com.order_service.mappers.order.OrderMapper;
import com.order_service.repositories.OrderRepository;
import com.order_service.services.kafka.order.KafkaOrderProducerService;
import com.order_service.services.rest_template.ProductRestTemplateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;
    private ProductRestTemplateService productRestTemplateService;
    private KafkaOrderProducerService kafkaOrderProducerService;

    @Override
    public OrderDto saveOrder(OrderDto orderDto) {

        Order order = OrderMapper.mapDtoToOrder(orderDto);

        //get product by id
        ProductDto productDto = productRestTemplateService.getProductById(orderDto.getProductId());
        log.trace("productDto: "+productDto);

        order.setProductId(productDto.getId());
        order.setProductName(productDto.getProductName());

        Order savedOrder = orderRepository.save(order);

        OrderDto savedOrderDto = OrderMapper.mapOrderToDto(savedOrder);

        kafkaOrderProducerService.sendOrderPlacedEvent(savedOrderDto);

        return savedOrderDto;

    }

    @Override
    @Cacheable(value = "orders", key = "#id")
    public OrderDto getOrder(String id) {

        Optional<Order> optionalOrder = orderRepository.findById(id);

        return OrderMapper.mapOrderToDto(optionalOrder.orElseGet(Order::new));
    }

    @Override
    @Cacheable(value = "orders", key = "'get-all-orders'")
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapper::mapOrderToDto).toList();
    }

    @Override
    @CachePut(value = "orders", key = "#orderDto.id")
    public OrderDto updateOrder(OrderDto orderDto) {

        Order savedOrder = null;

        Optional<Order> optionalOrder = orderRepository.findById(orderDto.getId());

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setTotalAmount(orderDto.getTotalAmount());
            order.setDiscountAmount(orderDto.getDiscountAmount());
            order.setFinalAmount(orderDto.getFinalAmount());
            order.setOrderStatus(orderDto.getOrderStatus());
            order.setPaymentStatus(orderDto.getPaymentStatus());
            order.setGift(orderDto.isGift());
            savedOrder = orderRepository.save(order);
        } else  {
           log.trace("order not found for id: "+orderDto.getId());
        }

        return OrderMapper.mapOrderToDto(savedOrder);
    }

    @Override
    @CacheEvict(value = "orders", key = "#id")
    public boolean deleteOrder(String id) {

        orderRepository.deleteById(id);

        return true;
    }
}
