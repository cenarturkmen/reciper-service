package com.feedmeApp.feedme.recipeCategory.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "categories")
public class RecipeCategory {
    @Id
    private String id;

    @Field("RecipeCategory")
    private String recipeCategory;

    public long recipeCount;
}
