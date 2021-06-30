package com.enigma.inventory.services;

import com.enigma.inventory.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product addProduct(Product product);
    public List<Product> getProduct();
    public void deleteProduct(String id);
    public Optional<Product> findProductById(String id);
}
