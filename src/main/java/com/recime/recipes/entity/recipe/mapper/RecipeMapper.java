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
			List<Ingredient> ingredients = dto.getIngredients().stream().map(RecipeMapper::ingredientDtoToEntity)
					.collect(Collectors.toList());
			recipe.setIngredients(ingredients);
		}

		if (dto.getInstructions() != null) {
			List<Instruction> instructions = dto.getInstructions().stream().map(RecipeMapper::instructionDtoToEntity)
					.collect(Collectors.toList());
			recipe.setIntructions(instructions);
		}

		return recipe;
	}

	public static Ingredient ingredientDtoToEntity(IngredientDTO dto) {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(dto.getId());
		ingredient.setName(dto.getName());
		ingredient.setQuantity(dto.getQuantity());
		ingredient.setUnit(dto.getUnit());
		return ingredient;
	}

	public static Instruction instructionDtoToEntity(InstructionDTO dto) {
		Instruction instruction = new Instruction();
		instruction.setId(dto.getId());
		instruction.setText(dto.getText());
		return instruction;
	}

	public static RecipeDTO toDto(Recipe entity) {
		if (entity == null) {
			return null;
		}

		RecipeDTO dto = new RecipeDTO();

		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setServings(entity.getServings());
		dto.setVegetarian(entity.isVegetarian());

		if (entity.getIngredients() != null) {
			List<IngredientDTO> ingredientDTOs = entity.getIngredients().stream()
					.map(RecipeMapper::ingredientEntityToDto).collect(Collectors.toList());
			dto.setIngredients(ingredientDTOs);
		}

		if (entity.getIntructions() != null) {
			List<InstructionDTO> instructionDTOs = entity.getIntructions().stream()
					.map(RecipeMapper::instructionEntityToDto).collect(Collectors.toList());
			dto.setInstructions(instructionDTOs);
		}

		return dto;
	}

	public static IngredientDTO ingredientEntityToDto(Ingredient entity) {
		if (entity == null) {
			return null;
		}

		IngredientDTO dto = new IngredientDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setQuantity(entity.getQuantity());
		dto.setUnit(entity.getUnit());
		return dto;
	}

	public static InstructionDTO instructionEntityToDto(Instruction entity) {
		if (entity == null) {
			return null;
		}

		InstructionDTO dto = new InstructionDTO();
		dto.setId(entity.getId());
		dto.setText(entity.getText());
		return dto;
	}
}
