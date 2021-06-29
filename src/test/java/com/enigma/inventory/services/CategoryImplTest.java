package com.enigma.inventory.services;

import com.enigma.inventory.entity.Category;
import com.enigma.inventory.repository.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryImplTest {

    @MockBean
    CategoryRepo repo;

    @Autowired
    CategoryService services;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void cleanAll(){repo.deleteAll();}

    @Test
    void addCategory() {
        Category category = new Category();
        category.setName("minuman");

        services.addCategory(category);


        List<Category>categories = new ArrayList<>();
        categories.add(category);

        when(repo.findAll()).thenReturn(categories);
        assertEquals(1, repo.findAll().size());
    }

    @Test
    void getCategory() {
        Category category = new Category();
        category.setName("minuman");

        repo.save(category);
        List<Category>categories = new ArrayList<>();
        categories.add(category);

        when(services.getCategory()).thenReturn(categories);
        assertEquals(1, services.getCategory().size());
    }

    @Test
    void deleteCategory() {
        Category category = new Category();
        category.setId("231ree");

        repo.save(category);
        services.deleteCategory("231ree");

        assertEquals(0, repo.findAll().size());
    }
}
