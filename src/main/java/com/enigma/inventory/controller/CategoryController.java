package com.enigma.inventory.controller;

import com.enigma.inventory.entity.Category;
import com.enigma.inventory.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService services;

    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return services.addCategory(category);
    }

    @GetMapping(produces = "application/json")
    public List<Category> getCategory(){
        return services.getCategory();
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable String id){
        services.deleteCategory(id);
    }
}
