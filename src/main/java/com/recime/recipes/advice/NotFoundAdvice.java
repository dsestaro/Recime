package com.recime.recipes.advice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.recime.recipes.entity.recipe.exception.IngredientNotFoundException;
import com.recime.recipes.entity.recipe.exception.InstructionNotFoundException;
import com.recime.recipes.entity.recipe.exception.RecipeNotFoundException;

@ControllerAdvice
public class NotFoundAdvice {

	@ResponseStatus(NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(RecipeNotFoundException.class)
	public String methodInvalidIdException(RecipeNotFoundException ex) {
		
		return ex.getMessage();
	}
	
	@ResponseStatus(NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(IngredientNotFoundException.class)
	public String methodInvalidIdException(IngredientNotFoundException ex) {
		
		return ex.getMessage();
	}
	
	@ResponseStatus(NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(InstructionNotFoundException.class)
	public String methodInvalidIdException(InstructionNotFoundException ex) {
		
		return ex.getMessage();
	}
}
