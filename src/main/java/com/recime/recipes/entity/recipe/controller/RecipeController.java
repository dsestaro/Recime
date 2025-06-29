package com.recime.recipes.entity.recipe.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public RecipeDTO createRecipe(@Valid @RequestBody RecipeDTO recipe) {

		log.info("Creating recipe {}.", recipe.getTitle());

		recipe = this.recipeService.create(recipe);

		log.info("Recipe {} created.", recipe.getTitle());

		return recipe;
	}

	@GetMapping(path = "/{id}")
	public RecipeDTO getRecipe(@PathVariable Integer id) {
		log.info("Retreiving recipe with ID {}.", id);

		RecipeDTO recipe = this.recipeService.findById(id);

		log.info("Recipe with ID {} found.", id);

		return recipe;
	}

	@PutMapping
	public RecipeDTO updateRecipe(@Valid @RequestBody RecipeDTO recipe) {
		log.info("Updating recipe {}.", recipe.getTitle());

		recipe = this.recipeService.update(recipe);

		log.info("Recipe {} updated.", recipe.getTitle());

		return recipe;
	}

	@DeleteMapping(path = "/{id}")
	public void deleteRecipe(@PathVariable Integer id) {
		log.info("Deleting recipe with ID {}.", id);

		this.recipeService.delete(id);

		log.info("Recipe with ID {} deleted.", id);
	}

	@GetMapping("/search")
	public List<RecipeDTO> searchRecipes(@RequestParam(required = false) Boolean vegetarian,
			@RequestParam(required = false) Integer servings,
			@RequestParam(required = false) List<String> includeIngredients,
			@RequestParam(required = false) List<String> excludeIngredients,
			@RequestParam(required = false) String instructionContains) {

		log.info(
				"Search recipes with filters - Vegetarian: {}, Servings: {}, "
						+ "IncludeIngredients: {}, ExcludeIngredients: {}, InstructionContains: {}",
				vegetarian, servings, includeIngredients, excludeIngredients, instructionContains);
		
		List<RecipeDTO> recipes = recipeService.searchRecipes(vegetarian, servings, 
				includeIngredients, excludeIngredients, instructionContains);
		
		log.debug("Recipes found: {}", recipes.size());
		
		return recipes;
	}
}
