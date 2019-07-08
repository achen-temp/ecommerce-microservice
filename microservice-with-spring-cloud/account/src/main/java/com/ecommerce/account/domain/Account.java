package com.ecommerce.account.domain;

import java.util.Objects;
import java.util.Set;

public class Account {

    private int id;
    private String name;
    private String address;
    private Set<Order> OrderHistory;
    private Wallet accountBalance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Order> getOrderHistory() {
        return OrderHistory;
    }

    public void setOrderHistory(Set<Order> orderHistory) {
        OrderHistory = orderHistory;
    }

    public Wallet getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Wallet accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getId() == account.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
