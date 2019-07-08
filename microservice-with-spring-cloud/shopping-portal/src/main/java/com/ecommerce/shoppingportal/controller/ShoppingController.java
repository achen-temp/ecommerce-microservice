package com.ecommerce.shoppingportal.controller;

import com.ecommerce.shoppingportal.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingController {

    @Autowired
    ShoppingService service;

    @GetMapping({"/", "/greeting", "/welcome"})
    public String welcome(){
        return "Welcome to e-commerce customer shopping portal";
    }

    /* For Hystrix */
    @GetMapping("/recommend/{category}")
    public String recommendProductByCategory(@PathVariable String category){
        return service.recommendProduct(category);
    }

    /* For Ribbon */
    @GetMapping("/account_server_info")
    public String getAccountInfoWithServer(){
        return service.getAccountServer();
    }

    @GetMapping("/order/{name}/{amount}")
    public String orderProduct(@PathVariable String name, @PathVariable int amount){
        return service.orderProduct(name, amount);
    }
}
