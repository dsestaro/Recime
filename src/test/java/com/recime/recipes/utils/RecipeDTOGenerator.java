package com.recime.recipes.utils;

import java.util.ArrayList;
import java.util.List;

import com.recime.recipes.entity.recipe.dto.IngredientDTO;
import com.recime.recipes.entity.recipe.dto.InstructionDTO;
import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.entity.recipe.model.ServingUnitEnum;

public class RecipeDTOGenerator {
	public static RecipeDTO populateRecipeDTO() {
		RecipeDTO recipeDTO = new RecipeDTO();
		
		recipeDTO.setId(10);
		recipeDTO.setTitle("Title");
		recipeDTO.setDescription("Description");
		recipeDTO.setVegetarian(true);
		recipeDTO.setServings(2);
		
		List<IngredientDTO> ingredients = new ArrayList<IngredientDTO>();
		
		IngredientDTO ingredientDTO = new IngredientDTO();
		ingredientDTO.setId(10);
		ingredientDTO.setName("Ingredient 1");
		ingredientDTO.setQuantity(200);
		ingredientDTO.setUnit(ServingUnitEnum.LITER);
		
		ingredients.add(ingredientDTO);
		
		ingredientDTO = new IngredientDTO();
		ingredientDTO.setId(11);
		ingredientDTO.setName("Ingredient 2");
		ingredientDTO.setQuantity(200);
		ingredientDTO.setUnit(ServingUnitEnum.PIECE);
		
		ingredients.add(ingredientDTO);
		
		recipeDTO.setIngredients(ingredients);
		
		List<InstructionDTO> instructions = new ArrayList<InstructionDTO>();
		
		for(int i = 0; i < 4; i++) {
			InstructionDTO instructionDTO = new InstructionDTO();
			
			instructionDTO.setId(i + 20);
			instructionDTO.setText("Instrucion " + i);
			
			instructions.add(instructionDTO);
		}
		
		recipeDTO.setInstructions(instructions);
		
		return recipeDTO;
	}
}
