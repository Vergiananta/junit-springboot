package com.enigma.inventory.services;

import com.enigma.inventory.entity.Category;
import com.enigma.inventory.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    CategoryRepo repo;

    @Override
    public Category addCategory(Category category) {
        return repo.save(category);
    }

    @Override
    public List<Category> getCategory() {
        return repo.findAll();
    }

    @Override
    public Optional<Category> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public void deleteCategory(String id) {
        repo.deleteById(id);
    }
}
