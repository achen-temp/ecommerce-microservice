package com.ecommerce.account.repository;

import com.ecommerce.account.domain.Account;
import com.ecommerce.account.domain.Order;
import com.ecommerce.account.domain.Wallet;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class AccountDAO {

    private static Wallet wallet;
    private static Account account;
    private static Set<Order> orderHistory;

    static{
        //initialize empty orderHistory;
        orderHistory = new HashSet<>();

        //Assume total is $200
        wallet = new Wallet();
        wallet.setCurrency("$");
        wallet.setBalance(200.00);

        //setup account information
        account = new Account();
        account.setId(1);
        account.setName("John Doe");
        account.setAddress("Hometown, HomeState");
        account.setAccountBalance(wallet);
        account.setOrderHistory(orderHistory);
    }

    /* Wallet Balance */
    public Wallet getWallet() {
        return wallet;
    }

    public void updateBalance(double expense) {
        wallet.setBalance(wallet.getBalance() - expense);
    }


    /* Order History */
    public Set<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addOrderToHistory(Order order){
        orderHistory.add(order);
    }


    /* Account */
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        AccountDAO.account = account;
    }

}
