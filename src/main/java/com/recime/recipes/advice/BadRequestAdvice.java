package com.recime.recipes.advice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.recime.recipes.entity.idempotency.exception.InvalidIdempotencyException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class BadRequestAdvice {

	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(InvalidIdempotencyException.class)
	public String methodInvalidDatesException(InvalidIdempotencyException ex) {
		
		log.warn(ex.getMessage());
		
		return ex.getMessage();
	}
}
