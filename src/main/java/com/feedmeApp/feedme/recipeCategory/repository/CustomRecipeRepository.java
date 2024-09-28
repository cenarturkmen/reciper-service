package com.feedmeApp.feedme.recipeCategory.repository;
import com.feedmeApp.feedme.recipe.model.Recipe;

import java.util.List;

public interface CustomRecipeRepository {

    List<Recipe> findByCategoryInAndIngredientsInAndCaloriesLessThanEqual(
            List<String> categories, List<String> ingredients, int calories);
}
