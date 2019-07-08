package com.ecommerce.product.kafka;

import com.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @Autowired
    ProductService service;

}
