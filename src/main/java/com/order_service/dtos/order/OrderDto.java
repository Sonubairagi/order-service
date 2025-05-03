package com.order_service.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private String id;
    private double totalAmount;
    private double discountAmount;
    private double finalAmount;
    private String orderStatus;           // e.g., PENDING, SHIPPED
    private String paymentStatus;
    private Date createdAt;
    private Date modifiedAt;
    private boolean isGift;
    private String productId;
    private String productName;
}
