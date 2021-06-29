package com.enigma.inventory.services;

import com.enigma.inventory.entity.Product;
import com.enigma.inventory.repository.ProductRepo;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ProductImplTest {

    @MockBean
    ProductRepo repo;

    @Autowired
    ProductService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    void addProduct() {
        Product product = new Product();
        product.setName("aqua");

        service.addProduct(product);
        repo.save(product);

        List<Product>products = new ArrayList<>();
        products.add(product);

        when(repo.findAll()).thenReturn(products);
        assertEquals(1, repo.findAll().size());
    }

    @Test
    void getProduct() {
        Product product = new Product();
        product.setName("aqua");

        repo.save(product);
        List<Product>products = new ArrayList<>();
        products.add(product);

        when(service.getProduct()).thenReturn(products);
        assertEquals(1, service.getProduct().size());
    }

    @Test
    void deleteProduct() {
        Product product = new Product();
        product.setId("123we");

        repo.save(product);
        service.deleteProduct("123we");

        assertEquals(0, repo.findAll().size());
    }
}
