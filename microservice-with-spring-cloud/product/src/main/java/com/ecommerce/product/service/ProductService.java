package com.ecommerce.product.service;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Set<Product> getAllProducts(){
        return repository.getAllProducts();
    }

    public Set<Product> getProductByCategory(String category) {
        return repository.getProductByCategory(category);
    }


    public boolean checkAvailability(String name, int amount){
        Product product = repository.getProductByName(name);
        if(product == null || product.getProductAmount() < amount){
            return false;
        }
        return true;
    }

    public double getPriceByProduct(String name) {
        Product product = repository.getProductByName(name);
        if(product != null){
            return product.getProductUnitPrice();
        }
        return -1;
    }

    public void updateProductInventory(Product purchasedProduct) {
        repository.updateInventory(purchasedProduct);
    }
}
