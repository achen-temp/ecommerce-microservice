package com.ecommerce.product.repository;

import com.ecommerce.product.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private static Set<Product> inventory;

    static {
        inventory = new HashSet<Product>();
        inventory.add(new Product("Book", "Java", 12.00, 50));
        inventory.add(new Product("Book", "JavaScript", 15.00, 10));
        inventory.add(new Product("Book", "Angular", 10.00, 20));
        inventory.add(new Product("Food", "Sandwich", 5.00, 30));
        inventory.add(new Product("Food", "Pizza", 6.99, 20));
        inventory.add(new Product("Food", "Apple", 1.99, 100));
        inventory.add(new Product("Movie", "Godzilla", 8.00, 30));
        inventory.add(new Product("Movie", "Avengers", 7.00, 20));
        inventory.add(new Product("Movie", "Batman", 10.00, 10));
    }


    public Set<Product> getAllProducts() {
        return inventory;
    }

    public Product getProductByName(String name){
        return inventory.stream().filter(
                item -> item.getProductName().equalsIgnoreCase(name)
        ).findAny().orElse(null);
    }

    public void updateInventory(Product purchasedProduct){
        String productName = purchasedProduct.getProductName();
        int purchasedAmount = purchasedProduct.getProductAmount();
        Iterator<Product> it = inventory.iterator();
        while (it.hasNext()){
            Product product = it.next();
            if(product.getProductName().equalsIgnoreCase(productName)){
                product.setProductAmount(product.getProductAmount() - purchasedAmount);
                return;
            }
        }
    }

    public Set<Product> getProductByCategory(String category) {
        return inventory.stream().filter(
                item -> item.getCategory().equalsIgnoreCase(category)
        ).collect(Collectors.toSet());
    }
}
