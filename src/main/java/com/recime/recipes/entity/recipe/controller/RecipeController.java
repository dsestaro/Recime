package com.recime.recipes.entity.recipe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.entity.recipe.service.RecipeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/recipe")
@AllArgsConstructor
public class RecipeController {
	
	RecipeService recipeService;
	
	@PostMapping
    public RecipeDTO createRecipe(@Valid @RequestBody RecipeDTO recipe){
		
		log.info("Creating recipe {}.", recipe.getTitle());
		
		recipe = this.recipeService.create(recipe);
		
		log.info("Recipe {} created.", recipe.getTitle());
		
		return recipe;
    }
	
	@GetMapping(path = "/{id}")
    public RecipeDTO getRecipe(@PathVariable Integer id){
		log.info("Retreiving recipe with ID {}.", id);
		
		RecipeDTO recipe = this.recipeService.findById(id);
		
		log.info("Recipe with ID {} found.", id);
		
		return recipe;
    }

}
