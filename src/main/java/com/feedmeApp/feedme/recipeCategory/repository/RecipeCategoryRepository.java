package com.feedmeApp.feedme.recipeCategory.repository;

import com.feedmeApp.feedme.recipeCategory.model.RecipeCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeCategoryRepository extends MongoRepository<RecipeCategory, String> {
    Page<RecipeCategory> findAll(Pageable pageable);
}