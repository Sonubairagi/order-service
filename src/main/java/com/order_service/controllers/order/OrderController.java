package com.order_service.controllers.order;

import com.order_service.dtos.order.OrderDto;
import com.order_service.services.order.OrderService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-service/api/v1")
public class OrderController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrderService orderService;

    @GetMapping("/app/heath-check")
    public ResponseEntity<?> checkAppHealth() {
        return new ResponseEntity<>("Fuck You!", HttpStatus.OK);
    }

    @GetMapping("/mongodb/health-check")
    public ResponseEntity<?> checkMongoDBHealth() {

        Document response = mongoTemplate.executeCommand("{ ping: 1}");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {

        OrderDto savedOrderDto = orderService.saveOrder(orderDto);

        return new ResponseEntity<>(savedOrderDto, HttpStatus.OK);
    }

    @GetMapping("/get-order/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String id) {

        OrderDto orderDto = orderService.getOrder(id);

        return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {

        List<OrderDto> allOrders = orderService.getAllOrders();

        return new ResponseEntity<List<OrderDto>>(allOrders, HttpStatus.OK);
    }

    @PutMapping("/update-order")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {

        OrderDto updatedOrder = orderService.updateOrder(orderDto);

        return new ResponseEntity<OrderDto>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/delete-order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {

        boolean isDeleted = orderService.deleteOrder(id);

        return new ResponseEntity<>( isDeleted ? "order is deleted!" : "order is not deleted!", HttpStatus.OK);
    }
}
