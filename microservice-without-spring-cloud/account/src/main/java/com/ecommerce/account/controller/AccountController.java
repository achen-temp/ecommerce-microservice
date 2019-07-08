package com.ecommerce.account.controller;

import com.ecommerce.account.domain.Account;
import com.ecommerce.account.domain.Order;
import com.ecommerce.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AccountController {

    @Autowired
    AccountService service;

    @Autowired
    Environment env;

    @GetMapping({"/","/account","/home"})
    public Account getAccountInfo(){
        return service.getAccountInfo();
    }

    @GetMapping("/order_history")
    public Set<Order> getOrderHistory(){
        return service.getOrderHistory();
    }

    @GetMapping("/balance_with_currency")
    public String checkBalanceWithCurrency(){
        return service.getCurrency() + " " + service.getBalance();
    }

    @GetMapping("/balance")
    public double checkBalance(){
        return service.getBalance();
    }

    @GetMapping("/account_server_info")
    public String getAccountServerInfo(){
        return service.getAccountInfo() + "; This account is retrieved from location: " + env.getProperty("server.port");
    }

    @PutMapping("/payment/{cost}")
    public String payment(@PathVariable double cost){
        if(checkBalance() < cost){
            return "FAILURE";
        }
        this.updateBalance(cost);
        return "SUCCESS";
    }


    public void updateBalance(double cost){
        service.updateBalance(cost);
    }

}
