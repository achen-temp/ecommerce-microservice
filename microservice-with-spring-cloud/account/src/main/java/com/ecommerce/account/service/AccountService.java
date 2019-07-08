package com.ecommerce.account.service;

import com.ecommerce.account.domain.Account;
import com.ecommerce.account.domain.Order;
import com.ecommerce.account.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountService {

    @Autowired
    AccountDAO repository;


    public Set<Order> getOrderHistory(){
        return repository.getOrderHistory();
    }

    public double getBalance() {
        return repository.getWallet().getBalance();
    }

    public String getCurrency(){
        return repository.getWallet().getCurrency();
    }

    public Account getAccountInfo() {
        return repository.getAccount();
    }

    public void updateBalance(double cost) {
        repository.updateBalance(cost);
    }

    public void updateOrderHistory(Order order) {
        repository.addOrderToHistory(order);
    }

}
