package com.enigma.inventory.services;

import com.enigma.inventory.entity.Product;
import com.enigma.inventory.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImpl implements ProductService{

    @Autowired
    ProductRepo repo;


    @Override
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public List<Product> getProduct() {
        return repo.findAll();
    }

    @Override
    public void deleteProduct(String id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return repo.findById(id);
    }
}
