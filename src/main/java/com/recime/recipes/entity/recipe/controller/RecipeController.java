package com.recime.recipes.entity.recipe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recime.recipes.entity.recipe.dto.RecipeDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
	
	@PostMapping
    public String createRecipe(@Valid @RequestBody RecipeDTO recipe){
		return "Testing";
    }
	
	@GetMapping
    public String getRecipe(){
		return "Testing";
    }

}
