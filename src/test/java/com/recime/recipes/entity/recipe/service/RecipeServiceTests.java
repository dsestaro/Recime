package com.recime.recipes.entity.recipe.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.recime.recipes.entity.recipe.dto.IngredientDTO;
import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.entity.recipe.exception.InvalidIdException;
import com.recime.recipes.utils.RecipeDTOGenerator;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTests {

	private RecipeService recipeService;
	
	@BeforeEach
	public void configure() {
		this.recipeService = new RecipeService();
	}
	
	@Test
	public void recipeCreationReturnErrorWhenUsingADTOWithARecipeId() {
		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();
		
		assertThrows(InvalidIdException.class, () -> this.recipeService.create(recipe));
	}
	
	@Test
	public void recipeCreationReturnErrorWhenUsingADTOWithAnIngredientId() {
		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();
		
		recipe.setId(null);
		
		assertThrows(InvalidIdException.class, () -> this.recipeService.create(recipe));
	}
	
	@Test
	public void recipeCreationReturnErrorWhenUsingADTOWithAnInstructionId() {
		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();
		
		recipe.setId(null);
		
		for(IngredientDTO ingredient : recipe.getIngredients()) {
			ingredient.setId(null);
		}
		
		assertThrows(InvalidIdException.class, () -> this.recipeService.create(recipe));
	}
}
