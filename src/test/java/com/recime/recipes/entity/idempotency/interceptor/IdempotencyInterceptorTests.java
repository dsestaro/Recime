package com.recime.recipes.entity.idempotency.interceptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.recime.recipes.entity.idempotency.exception.InvalidIdempotencyException;
import com.recime.recipes.entity.idempotency.model.CachedBodyHttpServletResponse;
import com.recime.recipes.entity.idempotency.model.Idempotency;
import com.recime.recipes.entity.idempotency.repository.IdempotencyRepository;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class IdempotencyInterceptorTests {
	
	@Mock
	private IdempotencyRepository respository;
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private CachedBodyHttpServletResponse response;

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
	
	@Test
	public void interceptorPreHandleShouldReturnFalseWhenAKeyAlreadyUsedKeyIsSent() throws IOException {
		
		doAnswer((Answer<String>) invocation -> {
			return "POST";
		}).when(this.request).getMethod();
		
		doAnswer((Answer<String>) invocation -> {
			return "10";
		}).when(this.request).getHeader("Idempotency-Key");
		
		doAnswer((Answer<Optional<Idempotency>>) invocation -> {
			
			Idempotency idempotency = new Idempotency();
			idempotency.setKey("10");
			idempotency.setStatus(202);
			idempotency.setResponse("");
			
			return Optional.of(idempotency);
		}).when(this.respository).findById(any());
		
		doAnswer((Answer<PrintWriter>) invocation -> {
			return mock(PrintWriter.class);
		}).when(this.response).getWriter();
		
		assertFalse(this.idempotencyInterceptor.preHandle(this.request, this.response, idempotencyInterceptor));
	}
	
	@Test
	public void interceptorPreHandleShouldReturnTrueWhenAKeyNewKeyIsSent() throws IOException {
		
		doAnswer((Answer<String>) invocation -> {
			return "POST";
		}).when(this.request).getMethod();
		
		doAnswer((Answer<String>) invocation -> {
			return "10";
		}).when(this.request).getHeader("Idempotency-Key");
		
		doAnswer((Answer<Optional<Idempotency>>) invocation -> {
			
			Idempotency idempotency = null;
			
			return Optional.ofNullable(idempotency);
		}).when(this.respository).findById(any());
		
		doAnswer((Answer<Idempotency>) invocation -> {
			
			Idempotency idempotency = new Idempotency();
			idempotency.setKey("10");
			idempotency.setStatus(202);
			idempotency.setResponse("");
			
			return idempotency;
		}).when(this.respository).save(any());
		
		assertTrue(this.idempotencyInterceptor.preHandle(this.request, this.response, idempotencyInterceptor));
	}
}
