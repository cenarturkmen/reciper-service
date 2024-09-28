package com.feedmeApp.feedme.recipe.repository.impl;

import com.feedmeApp.feedme.recipe.model.Recipe;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class RecipeRepositoryImpl implements CustomRecipeRepository {

    private final MongoTemplate mongoTemplate;

    public RecipeRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Recipe> findByCategoryInAndIngredientsInAndCaloriesLessThanEqual(
            List<String> categories, List<String> ingredients, int calories) {

        Query combinedQuery = new Query();

        combinedQuery.addCriteria(Criteria.where("calories").lte(calories));

        if (!categories.isEmpty()) {
            combinedQuery.addCriteria(Criteria.where("category").in(categories));
        }

        if (!ingredients.isEmpty()) {
            Criteria ingredientsCriteria = new Criteria().orOperator(
                    ingredients.stream()
                            .map(ingredient -> Criteria.where("ingredients").regex(ingredient, "i"))
                            .toArray(Criteria[]::new)
            );
            combinedQuery.addCriteria(ingredientsCriteria);
        }

        return mongoTemplate.find(combinedQuery, Recipe.class);
    }
}