package com.enigma.inventory.controller;

import com.enigma.inventory.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    ProductService services;

    @Autowired
    ProductController controller;

    @Test
    void addProduct() {
    }

    @Test
    void getProduct() {
    }

    @Test
    void deleteProduct() {
    }
}
