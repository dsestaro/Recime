package com.recime.recipes.entity.idempotency.interceptor;

import java.io.IOException;
import java.util.Optional;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.recime.recipes.entity.idempotency.exception.InvalidIdempotencyException;
import com.recime.recipes.entity.idempotency.model.CachedBodyHttpServletResponse;
import com.recime.recipes.entity.idempotency.model.Idempotency;
import com.recime.recipes.entity.idempotency.repository.IdempotencyRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class IdempotencyInterceptor implements HandlerInterceptor {

	private final IdempotencyRepository repository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		if (!"POST".equalsIgnoreCase(request.getMethod())) {
			return true;
		}

		String key = request.getHeader("Idempotency-Key");
		if (key == null || key.isBlank()) {
			throw new InvalidIdempotencyException();
		}

		Optional<Idempotency> existing = repository.findById(key);

		if (existing.isPresent()) {
			Idempotency cached = existing.get();
			response.setStatus(cached.getStatus());
			response.getWriter().write(cached.getResponse()); 
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
			
			response.getWriter().flush();
			
			((CachedBodyHttpServletResponse)response).copyBodyToResponse();
			return false;
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws IOException {
		if (!"POST".equalsIgnoreCase(request.getMethod())) {
			return;
		}

		String key = request.getHeader("Idempotency-Key");

		if (key == null || key.isBlank()) {
			return;
		}

		if (response instanceof CachedBodyHttpServletResponse) {
			CachedBodyHttpServletResponse wrappedResponse = (CachedBodyHttpServletResponse) response;
			String responseBody = wrappedResponse.getCapturedBodyAsString();

			Idempotency record = new Idempotency();
			record.setKey(key);
			record.setStatus(response.getStatus());
			record.setResponse(responseBody);

			repository.save(record);
		}
	}
}
