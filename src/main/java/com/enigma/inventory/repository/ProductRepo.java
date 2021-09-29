package com.enigma.inventory.repository;

import com.enigma.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, String> {
    public Product findProductByName(String name);
}
