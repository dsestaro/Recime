package com.recime.recipes.entity.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServingUnitEnum {
	
	GRAM("g"),
	KILOGRAM("kg"),
	MILLILITER("ml"),
	LITER("l"),
	PIECE("piece"),
	SLICE("slice"),
	CUP("cup"),
	TABLESPOON("tbsp"),
	TEASPOON("tsp"),
	PACKAGE("package"),
	BOTTLE("bottle"),
	CAN("can"),
	PORTION("portion"),
	SCOOP("scoop"),
	BAR("bar");
	
	private final String unit;
}
