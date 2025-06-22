package com.recime.recipes.entity.recipe.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.recime.recipes.entity.recipe.dto.IngredientDTO;
import com.recime.recipes.entity.recipe.dto.InstructionDTO;
import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.entity.recipe.model.Ingredient;
import com.recime.recipes.entity.recipe.model.Instruction;
import com.recime.recipes.entity.recipe.model.Recipe;

public class RecipeMapper {
	
	public static Recipe toEntity(RecipeDTO dto) {
        if (dto == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        
        recipe.setId(dto.getId());

        recipe.setTitle(dto.getTitle());
        recipe.setDescription(dto.getDescription());
        recipe.setServings(dto.getServings());
        recipe.setVegetarian(dto.isVegetarian());

        if (dto.getIngredients() != null) {
            List<Ingredient> ingredients = dto.getIngredients().stream()
                    .map(RecipeMapper::ingredientDtoToEntity)
                    .collect(Collectors.toList());
            recipe.setIngredients(ingredients);
        }

        if (dto.getInstructions() != null) {
            List<Instruction> instructions = dto.getInstructions().stream()
                    .map(RecipeMapper::instructionDtoToEntity)
                    .collect(Collectors.toList());
            recipe.setIntructions(instructions);
        }

        return recipe;
    }

    private static Ingredient ingredientDtoToEntity(IngredientDTO dto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(dto.getId());
        ingredient.setName(dto.getName());
        ingredient.setQuantity(dto.getQuantity());
        return ingredient;
    }

    private static Instruction instructionDtoToEntity(InstructionDTO dto) {
        Instruction instruction = new Instruction();
        instruction.setId(dto.getId());
        instruction.setText(dto.getText());
        return instruction;
    }
}
