package com.enigma.inventory.controller;

import com.enigma.inventory.entity.Product;
import com.enigma.inventory.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService services;

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return services.addProduct(product);
    }

    @GetMapping(produces = "application/json")
    public List<Product> getProduct() {
        return services.getProduct();
    }

    @GetMapping("/name")
    public Product getProductByName(@RequestParam String name) {
        return services.getProductByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        services.deleteProduct(id);
    }
}
