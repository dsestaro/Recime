package com.recime.recipes.entity.recipe.exception;

public class RecipeNotFoundException extends RuntimeException {
	
	public RecipeNotFoundException(String message) {
        super(message);
    }
}
