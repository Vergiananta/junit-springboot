package com.enigma.inventory.controller;

import com.enigma.inventory.entity.Category;
import com.enigma.inventory.entity.Product;
import com.enigma.inventory.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @MockBean
    ProductService services;

    @Autowired
    ProductController controller;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void addProduct() throws Exception {
        Category category = new Category("ret123","makanan");
        Product product = new Product("A01", "beng beng", 2000.00, 12, category);
        given(services.addProduct(any(Product.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(product)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(product.getName())))
                .andExpect(jsonPath("$.price", Matchers.is(product.getPrice())))
                .andExpect(jsonPath("$.stock", Matchers.is(product.getStock())));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getProduct() throws Exception {
        Category category = new Category("ret123","makanan");
        Product product = new Product("beng beng", 2000.00, 12, category);
        List<Product> productList = Arrays.asList(product);

        given(services.getProduct()).willReturn(productList);

        mockMvc.perform(get("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(product.getName())))
                .andExpect(jsonPath("$[0].price", Matchers.is(product.getPrice())))
                .andExpect(jsonPath("$[0].stock", Matchers.is(product.getStock())));
    }

    @Test
    void deleteProduct() throws Exception {
        String productId = "A01";
        Category category = new Category("ret123","makanan");
        Product product = new Product(productId, "beng beng", 2000.00, 12, category);
        given(services.findProductById(productId)).willReturn(Optional.of(product));
        doNothing().when(services).deleteProduct(product.getId());

        this.mockMvc.perform(delete("/product/{id}", product.getId()))
                .andExpect(status().isOk());
    }
}
