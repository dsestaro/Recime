package com.recime.recipes.entity.recipe.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructionDTO {
	
	private int id;
	
	@NotEmpty(message = "Instruction text cannot be empty.")
	private String text;
}
