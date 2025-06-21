package com.recime.recipes.entity.idempotency.exception;


public class InvalidImdepotencyException extends RuntimeException {
	
	public InvalidImdepotencyException() {
        super("Idempotency-Key header not found.");
    }
}
