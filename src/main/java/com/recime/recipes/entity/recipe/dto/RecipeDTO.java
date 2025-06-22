package com.recime.recipes.entity.recipe.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO {
	
	private Integer id;
	
	@NotEmpty(message = "Recipe title cannot be empty.")
	private String title;
	
	@NotEmpty(message = "Recipe description cannot be empty.")
	private String description;

	@Min(value = 1, message = "The recipe must contain at least one serving.")
	private int servings;
	
	private boolean vegetarian;
	
	@Size(min=1, message = "The recipe must contain at least one ingredient.")
	@NotNull(message = "The recipe must contain at least one ingredient.")
	@Valid
	private List<IngredientDTO> ingredients;
	
	@Size(min=1, message = "The recipe must contain at least one instruction.")
	@NotNull(message = "The recipe must contain at least one instruction.")
	@Valid
	private List<InstructionDTO> instructions;
}
