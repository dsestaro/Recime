package com.recime.recipes.entity.recipe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO {
	private Integer id;
	
	private String name;
	
	private String unit;
	
	private double quantity;
}
