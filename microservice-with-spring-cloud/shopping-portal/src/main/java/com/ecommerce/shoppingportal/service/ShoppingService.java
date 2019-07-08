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

    /* Hystrix : hystrix command with fallback*/
    @HystrixCommand(fallbackMethod = "recommendationFallback")
    public String recommendProduct(String category) {
        String url = "http://product-service/recommend/" + category;
        String recommended = restTemplate.getForObject(url, String.class);
        return recommended;
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
     * */
    public String getAccountServer() {
        String url = "http://account-service/account_server_info/";
        String account_server_info = restTemplate.getForObject(url, String.class);
        return account_server_info;
    }


    /**
     * Logic
     * step1: call product service to check if the product is available with amount, if so, return unit price
     * step2: call account to reduce that total cost
     * step3: call Kafka to reduce product service inventory
     * step4: call Kafka to add new order into order history
     * */
    public String orderProduct(String name, int amount) {
        //step1
        String eligibilityUrl = "http://product-service/price/" + name + "/" + amount;
        double unitPrice = 0;
        try{
            unitPrice = restTemplate.getForObject(eligibilityUrl, Double.class);
        }catch (Exception e){
            return "Sorry, the product: " + name + " is not available for the amount: " + amount;
        }

        //step2
        try {
            String costUrl = "http://account-service/payment/" + amount * unitPrice;
            //String updateBalanceStatus = restTemplate.getForObject(costUrl, String.class);
           // String updateBalanceStatus = restTemplate.put(costUrl, null, String.class);
            HttpEntity<String> response = restTemplate.exchange(costUrl, HttpMethod.PUT, null, String.class);
            String updateBalanceStatus = response.getBody();
            if (!"SUCCESS".equalsIgnoreCase(updateBalanceStatus)) {
                return "Sorry, there is an error with your payment! Please try again later";
            }
        }catch (Exception e){
            return "Sorry, there is an error with your payment! Please try again later";
        }

        Order order = new Order();
        order.setProductName(name);
        order.setProductAmount(amount);
        String orderString = this.convertObjectToJsonString(order);
        //step3 - reduce inventory
        this.subtractProductInventory(orderString);

        //step4 - update account order history
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        order.setProductUnitPrice(unitPrice);
        order.setOrderDate(timestamp);
        order.setTaxRate(TAX_RATE);
        order.setTotalPrice(amount * unitPrice);
        order.setOrderId(name+ timestamp.getTime());

        String orderHistory = this.convertObjectToJsonString(order);
        this.updateAccountOrderHistory(orderHistory);

        return "Thanks for your Order " + order.toString();
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
