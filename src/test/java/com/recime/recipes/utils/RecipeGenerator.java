package com.recime.recipes.utils;

import java.util.ArrayList;
import java.util.List;

import com.recime.recipes.entity.recipe.model.Ingredient;
import com.recime.recipes.entity.recipe.model.Instruction;
import com.recime.recipes.entity.recipe.model.Recipe;
import com.recime.recipes.entity.recipe.model.ServingUnitEnum;

public class RecipeGenerator {
	public static Recipe populateRecipe() {
		Recipe recipe = new Recipe();
		
		recipe.setId(10);
		recipe.setTitle("Title");
		recipe.setDescription("Description");
		recipe.setVegetarian(true);
		recipe.setServings(2);
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(10);
		ingredient.setName("Ingredient 1");
		ingredient.setQuantity(200);
		ingredient.setUnit(ServingUnitEnum.LITER);
		
		ingredients.add(ingredient);
		
		ingredient = new Ingredient();
		ingredient.setId(11);
		ingredient.setName("Ingredient 2");
		ingredient.setQuantity(200);
		ingredient.setUnit(ServingUnitEnum.PIECE);
		
		ingredients.add(ingredient);
		
		recipe.setIngredients(ingredients);
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		
		for(int i = 0; i < 4; i++) {
			Instruction instruction = new Instruction();
			
			instruction.setId(i + 20);
			instruction.setText("Instrucion " + i);
			
			instructions.add(instruction);
		}
		
		recipe.setInstructions(instructions);
		
		return recipe;
	}
}
