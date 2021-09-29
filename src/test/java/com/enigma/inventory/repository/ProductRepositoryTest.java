package com.enigma.inventory.repository;

import com.enigma.inventory.entity.Category;
import com.enigma.inventory.entity.Product;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ProductRepo repo;

    @Autowired
    CategoryRepo categoryRepo;

    @Test
    public void whenFindByName_thenReturnProduct(){
        Product product = new Product("A012", "tango", 2000.0, 32);
        repo.save(product);

        Product found = repo.findProductByName(product.getName());

        assertThat(found.getName()).isEqualTo(product.getName());
    }

    @Test
    void saveRepository_thenReturnProduct(){
        Product product = new Product("A01", "tango", 2000.0, 32);
        Product saving = repo.save(product);
        assertNotNull(entityManager.find(Product.class, saving.getId()));
    }
}
