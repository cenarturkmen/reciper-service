package com.feedmeApp.feedme.recipe.repository.impl;

import com.feedmeApp.feedme.recipe.model.Recipe;

import java.util.List;

public interface CustomRecipeRepository {

    List<Recipe> findByCategoryInAndIngredientsInAndCaloriesLessThanEqual(
            List<String> categories, List<String> ingredients, int calories);
}