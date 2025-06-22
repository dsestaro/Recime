package com.recime.recipes.advice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.recime.recipes.entity.recipe.exception.RecipeNotFoundException;

@ControllerAdvice
public class NotFoundAdvice {

	@ResponseStatus(NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(RecipeNotFoundException.class)
	public void methodInvalidIdempotencyException(RecipeNotFoundException ex) {
		
		return;
	}
}
