package com.recime.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.recime.recipes.entity.recipe.model.Recipe;

@SpringBootApplication
public class RecipesApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(RecipesApplication.class, args);
	}
}
