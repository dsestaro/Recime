package com.recime.recipes.entity.recipe.exception;

import lombok.Getter;

@Getter
public class InvalidIdException extends RuntimeException {
	
	private String field;
	
	public InvalidIdException(String field, Integer integer) {
        super(String.format("Client must not provide an %s ID %s when creating a new recipe.", field, integer));
        
        this.field = field;
    }

	public InvalidIdException(String field) {
		super(String.format("Client must provide an %s ID when updating a recipe.", field));
        
        this.field = field;
	}
}