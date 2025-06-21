package com.recime.recipes.entity.recipe.dto;

import com.recime.recipes.entity.recipe.model.ServingUnitEnum;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO {
	private Integer id;
	
	@NotEmpty(message = "Ingredient name cannot be empty.")
	private String name;
	
	@NotNull(message = "Ingredient unity cannot be null.")
	private ServingUnitEnum unit;
	
	@Min(value = 1, message = "The ingredient quantity cannot be empty.")
	private double quantity;
}
