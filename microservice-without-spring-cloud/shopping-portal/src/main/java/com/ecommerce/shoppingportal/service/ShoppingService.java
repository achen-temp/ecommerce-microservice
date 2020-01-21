package com.ecommerce.shoppingportal.service;

import org.springframework.stereotype.Service;

@Service
public class ShoppingService {

    public static final double TAX_RATE = 6.25;

    public String recommendProduct(String category) {
        //TODO:: use restTemplate to call product service to get recommended product
        return "Recommended Product is not available";
    }

    public String getAccountServer() {
        //TODO:: use restTemplate to call account service to get account info
        return "Account Info is not available";
    }


    /**
     * Logic
     * step1: call product service to check if the product is available with amount, if so, return unit price
     * step2: call account to reduce that total cost
     * step3: call Kafka to reduce product service inventory
     * step4: call Kafka to add new order into order history
     * */
    public String orderProduct(String name, int amount) {
        //TODO:: Order product logic
        return "Thanks for your Order ";
    }

}
