package com.feedmeApp.feedme.recipe.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "recipes")
public class Recipe {
    @Id
    private String id;
    private String title;
    private String category;
    private String author;
    private String description;
    private double rating;
    private int rating_count;
    private int review_count;
    private String ingredients;
    private String directions;
    private String prep_time;
    private String cook_time;
    private String total_time;
    private int servings;
    private String yields;
    private double calories;
    private double carbohydrates_g;
    private double sugars_g;
    private double fat_g;
    private double protein_g;
    private double dietary_fiber_g;
    private double calories_from_fat;
    private String image;
}
