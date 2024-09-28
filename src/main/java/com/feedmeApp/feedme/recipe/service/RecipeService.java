package com.feedmeApp.feedme.recipe.service;

import com.feedmeApp.feedme.common.SearchRecipe;
import com.feedmeApp.feedme.recipe.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeService {
    Page<Recipe> getAllRecipes(Pageable pageable);
    Recipe getRecipeById(int id);
    Page<Recipe> getRecipesByCategory(String categoryName, Pageable pageable);
    List<String> getAllCategories();
    List<Recipe> searchRecipesWithParameters(SearchRecipe searchRecipeDto);
}