package com.recime.recipes.advice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.recime.recipes.advice.model.ErrorMessage;
import com.recime.recipes.entity.idempotency.exception.InvalidIdempotencyException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class BadRequestAdvice {

	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(InvalidIdempotencyException.class)
	public ErrorMessage methodInvalidDatesException(InvalidIdempotencyException ex) {
		
		log.warn(ex.getMessage());
		
		ErrorMessage message = new ErrorMessage();
		
		message.setField("Idempotency-Key");
		message.setMessage(ex.getMessage());
		
		return message;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public List<ErrorMessage> validationError(MethodArgumentNotValidException ex) {
	    BindingResult result = ex.getBindingResult();
	    final List<FieldError> fieldErrors = result.getFieldErrors();
	    List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
	    
	    for(FieldError error : fieldErrors) {
	    	ErrorMessage message = new ErrorMessage();
			
			message.setField(error.getField());
			message.setMessage(error.getDefaultMessage());
			
			messages.add(message);
	    }
	    
	    return messages;
	}
}
