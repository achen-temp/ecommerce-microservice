package com.ecommerce.shoppingportal.service;

import com.ecommerce.shoppingportal.domain.Order;
import com.ecommerce.shoppingportal.kafka.ShoppingProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@Service
public class ShoppingService {

    public static final double TAX_RATE = 6.25;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ShoppingProducer producer;

    /**
     *  Hystrix : hystrix command with fallback
     *  use restTemplate to call product service "recommend" rest api
     *  */
    public String recommendProduct(String category) {
        //TODO:
        return "";
    }

    /**
     * Hystrix fallback method
     * this method must take same input as the non-fallback method
     **/
    public String recommendationFallback(String category){
        return "Sorry, we cannot find any product belong to the category : " + category;
    }

    /**
     * Ribbon to respond account info and server info
     * Use RestTemplate to call account service account_server_info API
     * */
    public String getAccountServer() {
        //TODO:
        return "";
    }


    /**
     * Main Logic
     * step1: call product service to check if the product is available with amount, if so, return unit price
     * step2: call account to reduce that total cost
     * step3: call Kafka to reduce product service inventory
     * step4: call Kafka to add new order into order history
     * */
    public String orderProduct(String name, int amount) {
        //TODO:
        return "";
    }


    /* product-service */
    public void subtractProductInventory(String purchasedProduct){
        producer.sendProductInventoryReduction(purchasedProduct);
    }

    /* account-service*/
    public void updateAccountOrderHistory(String orderHistory){
        producer.sendAccountOrderHistoryUpdate(orderHistory);
    }



    public String convertObjectToJsonString(Object o){
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try{
            result = mapper.writeValueAsString(o);
        }catch (Exception e){
            System.out.println("Failed to convert Object to Json String");
        }
        return result;
    }
}
