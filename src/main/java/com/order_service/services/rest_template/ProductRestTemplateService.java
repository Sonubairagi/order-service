package com.order_service.services.rest_template;

import com.order_service.dtos.product.ProductDto;

public interface ProductRestTemplateService {

    ProductDto getProductById(String id);
}
