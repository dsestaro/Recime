package com.recime.recipes.advice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorMessage {

	private String field;
	
	private String message;
}
