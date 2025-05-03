package com.order_service.services.rest_template;

import com.order_service.dtos.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class ProductRestTemplateServiceImpl implements ProductRestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String getProductURL;

    @Override
    public ProductDto getProductById(String id) {

        try {
            return restTemplate.getForObject(getProductURL + id, ProductDto.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new RuntimeException("Product not found with ID: " + id);
        } catch (Exception ex) {
            throw new RuntimeException("Error communicating with Product Service: " + ex.getMessage());
        }
    }
}
