package com.feedmeApp.feedme.recipeCategory.controller;

import com.feedmeApp.feedme.recipeCategory.model.RecipeCategory;
import com.feedmeApp.feedme.recipeCategory.repository.RecipeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeCategoryController {
    private final RecipeCategoryRepository recipeCategoryRepository;


    @Autowired
    public RecipeCategoryController(RecipeCategoryRepository recipeCategoryRepository) {
        this.recipeCategoryRepository = recipeCategoryRepository;
    }
    @GetMapping(path="/getAllCategories")
    public List<RecipeCategory> getAllCategories() {
        return recipeCategoryRepository.findAll();
    }

}
