package com.recime.recipes.entity.recipe.exception;

public class InvalidIdLinkException extends RuntimeException {
	private String field;
	
	public InvalidIdLinkException(String field, Integer id) {
        super(String.format("Id %d informed in field %s ID  doesnt belong to the informed recipe.", id, field));
        
        this.field = field;
    }
}
