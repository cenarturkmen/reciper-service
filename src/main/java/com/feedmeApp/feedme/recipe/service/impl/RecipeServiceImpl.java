package com.feedmeApp.feedme.recipe.service.impl;

import com.feedmeApp.feedme.exceptions.ResourceNotFoundException;
import com.feedmeApp.feedme.recipe.model.Recipe;
import com.feedmeApp.feedme.recipe.repository.RecipeRepository;
import com.feedmeApp.feedme.recipe.service.RecipeService;
import com.feedmeApp.feedme.common.SearchRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, MongoTemplate mongoTemplate) {
        this.recipeRepository = recipeRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Recipe> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    @Override
    public Recipe getRecipeById(int id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id " + id));
    }

    @Override
    public Page<Recipe> getRecipesByCategory(String categoryName, Pageable pageable) {
        return recipeRepository.findByCategory(categoryName, pageable);
    }

    @Override
    public List<String> getAllCategories() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("category").first("category").as("category")
        );

        AggregationResults<CategoryResult> results = mongoTemplate.aggregate(aggregation, "recipes", CategoryResult.class);

        return results.getMappedResults().stream()
                .map(CategoryResult::getCategory)
                .distinct()
                .toList();
    }

    @Override
    public List<Recipe> searchRecipesWithParameters(SearchRecipe searchRecipeDto) {
        return recipeRepository.findByCategoryInAndIngredientsInAndCaloriesLessThanEqual(
                searchRecipeDto.getCategories(),
                searchRecipeDto.getIngredients(),
                searchRecipeDto.getCalories()
        );
    }

    public static class CategoryResult {
        private String category;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
}