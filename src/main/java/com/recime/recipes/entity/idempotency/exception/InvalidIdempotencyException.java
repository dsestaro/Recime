package com.recime.recipes.entity.idempotency.exception;


public class InvalidIdempotencyException extends RuntimeException {
	
	public InvalidIdempotencyException() {
        super("Idempotency-Key header not found.");
    }
}
