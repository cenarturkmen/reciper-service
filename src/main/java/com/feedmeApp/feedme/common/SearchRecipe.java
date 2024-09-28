package com.feedmeApp.feedme.common;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SearchRecipe {
    private ArrayList<String> categories;
    private ArrayList<String> ingredients;
    private int calories;
}
