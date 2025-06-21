package com.recime.recipes.entity.idempotency.interceptor;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.recime.recipes.entity.idempotency.exception.InvalidIdempotencyException;
import com.recime.recipes.entity.idempotency.repository.IdempotencyRespository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class IdempotencyInterceptor implements HandlerInterceptor {

	private IdempotencyRespository respository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		if (!"POST".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
		
		log.debug("Validating if request contains Idempotency-Key header.");
		
		String key = request.getHeader("Idempotency-Key");
		
		if(key == null) {
			log.warn("Idempotency-Key header not found.");
			throw new InvalidIdempotencyException();
		}
		
		return true;
	}
}
