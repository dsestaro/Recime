package com.recime.recipes.entity.recipe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
	
	@GetMapping
    public String createRecipe(){
		return "Testing";
    }

}
