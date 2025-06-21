package com.recime.recipes.entity.recipe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTests {

	@Autowired
    private MockMvc mockMvc;

	@Test
	public void recipeCreationReturnBadRequestInCaseOfNoIdempotencyHeader() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest());
	}
}
