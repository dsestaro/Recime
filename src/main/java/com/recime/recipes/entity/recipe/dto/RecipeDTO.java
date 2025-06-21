package com.recime.recipes.entity.recipe.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO {
	
	private Integer id;
	
	private String title;
	
	private String description;

	private int servings;
	
	private boolean vegetarian;
	
	private List<IngredientDTO> ingredients;
	
	private List<InstructionDTO> instructions;
}
