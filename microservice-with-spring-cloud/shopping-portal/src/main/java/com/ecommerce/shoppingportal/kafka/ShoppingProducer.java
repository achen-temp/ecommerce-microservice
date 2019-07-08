package com.ecommerce.shoppingportal.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShoppingProducer {

    private final KafkaTemplate kafkaTemplate;
    private static final String INVENTORY_TOPIC = "########-inventory";
    private static final String ACCOUNT_TOPIC = "########-account";

    /* Spring autoconfig kafkatemplate - no customized initialization needed*/
    @Autowired
    public ShoppingProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*update inventory*/
    public void sendProductInventoryReduction(String product){
        this.kafkaTemplate.send(INVENTORY_TOPIC, product);
        System.out.println(">>>KAFKA<<< ::: Send To Product Service with purchased product : " + product);
    }

    /*update order history*/
    public void sendAccountOrderHistoryUpdate(String order){
        this.kafkaTemplate.send(ACCOUNT_TOPIC, order);
        System.out.println(">>>KAFKA<<< ::: Send To Account Service with Order Record : " + order);
    }
}
