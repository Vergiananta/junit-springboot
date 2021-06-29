package com.enigma.inventory.services;

import com.enigma.inventory.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Category addCategory(Category category);
    public List<Category> getCategory();
    public Optional<Category> findById(String id);
    public void deleteCategory(String id);
}
