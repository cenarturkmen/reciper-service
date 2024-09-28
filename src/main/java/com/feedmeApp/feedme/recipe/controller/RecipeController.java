package com.feedmeApp.feedme.recipe.controller;

import com.feedmeApp.feedme.recipe.model.Recipe;
import com.feedmeApp.feedme.common.SearchRecipe;
import com.feedmeApp.feedme.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(path="/getAllRecipes")
    public Page<Recipe> getAllRecipes(Pageable pageable){
        return recipeService.getAllRecipes(pageable);
    }

    @GetMapping("/findByRecipeId/{id}")
    public Recipe getRecipeByRecipeId(@PathVariable int id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping(path = "/getRecipesByCategory/{categoryName}")
    public Page<Recipe> getRecipesByCategory(@PathVariable String categoryName,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return recipeService.getRecipesByCategory(categoryName, pageable);
    }

    @GetMapping(path="/getAllCategoriesTest")
    public List<String> getAllCategoriesTest() {
        return recipeService.getAllCategories();
    }

    @PostMapping(path="/searchRecipesWithParameters/")
    public List<Recipe> searchRecipesWithParameters(@RequestBody SearchRecipe searchRecipeDto){
        return recipeService.searchRecipesWithParameters(searchRecipeDto);
    }
}
