package com.order_service.entities.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

//    private String orderNumber;
//    private String customerId;

//    private List<Product> products;

    private double totalAmount;
    private double discountAmount;
    private double finalAmount;

    private String orderStatus;           // e.g., PENDING, SHIPPED
    private String paymentStatus;    // e.g., PAID, UNPAID
//    private String paymentMethod;    // e.g., CARD, PAYPAL

//    private Address shippingAddress;
//    private Address billingAddress;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date modifiedAt;

//    private LocalDateTime deliveryDate;

//    private String trackingNumber;

//    private String notes;
    private boolean isGift;

    private String productId;
    private String productName;
}
