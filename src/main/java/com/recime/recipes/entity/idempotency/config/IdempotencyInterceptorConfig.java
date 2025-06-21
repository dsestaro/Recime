package com.recime.recipes.entity.idempotency.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.recime.recipes.entity.idempotency.interceptor.IdempotencyInterceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@AllArgsConstructor
public class IdempotencyInterceptorConfig implements WebMvcConfigurer {

	private IdempotencyInterceptor idempotencyInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(idempotencyInterceptor);
    }
}
