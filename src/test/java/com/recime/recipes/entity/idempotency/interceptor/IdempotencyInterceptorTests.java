package com.recime.recipes.entity.idempotency.interceptor;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.recime.recipes.entity.idempotency.exception.InvalidIdempotencyException;
import com.recime.recipes.entity.idempotency.repository.IdempotencyRespository;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class IdempotencyInterceptorTests {
	
	@Mock
	private IdempotencyRespository respository;
	
	@Mock
	private HttpServletRequest request;

	private IdempotencyInterceptor idempotencyInterceptor;
	
	@BeforeEach
	public void configure() {
		this.idempotencyInterceptor = new IdempotencyInterceptor(respository);
	}
	
	@Test
	public void interceptorPreHandleShouldReturnSuccessIfMethodIsNotAPost() throws IOException {
		
		doAnswer((Answer<String>) invocation -> {
			return "GET";
		}).when(this.request).getMethod();
		
		assertTrue(this.idempotencyInterceptor.preHandle(this.request, null, idempotencyInterceptor));
	}
	
	@Test
	public void interceptorPreHandleShouldReturnAnErrorWhenNoIdempotecyKeyHeaderIsPassed() throws IOException {
		
		doAnswer((Answer<String>) invocation -> {
			return "POST";
		}).when(this.request).getMethod();
		
		doAnswer((Answer<String>) invocation -> {
			return null;
		}).when(this.request).getHeader("Idempotency-Key");
		
		assertThrows(InvalidIdempotencyException.class, () -> this.idempotencyInterceptor.preHandle(this.request, null, idempotencyInterceptor));
	}
}
