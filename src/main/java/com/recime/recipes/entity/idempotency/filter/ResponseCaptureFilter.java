package com.recime.recipes.entity.idempotency.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.recime.recipes.entity.idempotency.model.CachedBodyHttpServletResponse;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

@Component
@WebFilter(urlPatterns = "/*")
public class ResponseCaptureFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (response instanceof HttpServletResponse) {
            CachedBodyHttpServletResponse wrappedResponse = new CachedBodyHttpServletResponse((HttpServletResponse) response);
            chain.doFilter(request, wrappedResponse);
            wrappedResponse.copyBodyToResponse();  // Important: Write captured body back to client
        } else {
            chain.doFilter(request, response);
        }
    }
}
