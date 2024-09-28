package com.feedmeApp.feedme.recipe.repository;

import com.feedmeApp.feedme.recipe.model.Recipe;
import com.feedmeApp.feedme.recipeCategory.repository.CustomRecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String>, CustomRecipeRepository {
    Page<Recipe> findAll(Pageable pageable);
    Optional<Recipe> findById(int id);
    @Query(value = "{ 'RecipeCategory': ?0 }", count = true)
    long countByRecipeCategory(String recipeCategory);
    Page<Recipe> findByCategory(String categoryName, Pageable pageable);
    List<Recipe> findByCategoryInAndIngredientsInAndCaloriesLessThanEqual(
            List<String> categories, List<String> ingredients, int calories
    );
}