package com.enigma.inventory.controller;

import com.enigma.inventory.entity.Category;
import com.enigma.inventory.services.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.units.qual.A;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @MockBean
    CategoryService service;

    @Autowired
    CategoryController controller;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;


    @Test
    void addCategory() throws Exception {
        Category category = new Category("ret123","makanan");
        given(service.addCategory(any(Category.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(category)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(category.getName())));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldReturnDefaultData() throws Exception {
        Category category = new Category("minuman");
        List<Category> categoryList = Arrays.asList(category);

        given(service.getCategory()).willReturn(categoryList);

        mockMvc.perform(get("/category")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(category.getName())));
    }

    @Test
    void deleteCategory() throws Exception {
        String categoryId = "ret123";
        Category category = new Category(categoryId, "makanan");
        given(service.findById(categoryId)).willReturn(Optional.of(category));
        doNothing().when(service).deleteCategory(category.getId());

        this.mockMvc.perform(delete("/category/{id}", category.getId()))
                .andExpect(status().isOk());
    }
}
