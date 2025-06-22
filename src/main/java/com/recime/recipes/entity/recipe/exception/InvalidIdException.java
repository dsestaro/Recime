package com.recime.recipes.entity.recipe.exception;

import lombok.Getter;

@Getter
public class InvalidIdException extends RuntimeException {
	
	private String field;
	
	public InvalidIdException(String field) {
        super(String.format("Client must not provide an %s ID when creating a new recipe.", field));
        
        this.field = field;
    }
}