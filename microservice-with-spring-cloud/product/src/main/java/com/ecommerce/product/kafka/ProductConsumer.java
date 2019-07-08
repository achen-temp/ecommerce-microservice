package com.ecommerce.product.kafka;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @Autowired
    ProductService service;

    @KafkaListener(topics = "########-inventory")
    public void processPayload(String payload){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Product purchasedProduct = mapper.readValue(payload, Product.class);
            service.updateProductInventory(purchasedProduct);
        }catch (Exception e){
            System.out.println("Error Converting payload to Product object");
        }
    }
}
