package com.recime.recipes.entity.recipe.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.recime.recipes.entity.recipe.dto.IngredientDTO;
import com.recime.recipes.entity.recipe.dto.InstructionDTO;
import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.utils.RecipeDTOGenerator;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RecipeControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void recipeCreationReturnBadRequestInCaseOfNoIdempotencyHeader() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = post("/recipe").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfNoBody() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingTitle() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setTitle(null);

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("title")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyTitle() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setTitle("");

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("title")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingDescription() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setDescription(null);

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("description")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyDescription() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setDescription("");

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("description")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingInstructions() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setInstructions(null);

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("instructions")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyInstructions() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setInstructions(new ArrayList<InstructionDTO>());

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("instructions")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingInstructionText() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.getInstructions().get(0).setText(null);

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("instructions[0].text")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyInstructionsText() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.getInstructions().get(0).setText("");

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("instructions[0].text")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingIngredients() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setIngredients(null);

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("ingredients")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyIngredients() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setIngredients(new ArrayList<IngredientDTO>());

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("ingredients")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingIngredientName() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.getIngredients().get(0).setName(null);

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("ingredients[0].name")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyIngredientName() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.getIngredients().get(0).setName("");

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("ingredients[0].name")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingIngredientQuantity() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.getIngredients().get(0).setQuantity(0);

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("ingredients[0].quantity")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingIngredientUnit() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.getIngredients().get(0).setUnit(null);

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field", is("ingredients[0].unit")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfPresenceOfRecipeId() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.field", is("recipe")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfPresenceOfIngredientId() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setId(null);

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.field", is("ingredient")));
	}

	@Test
	public void recipeCreationReturnBadRequestInCaseOfPresenceOfInstructionId() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setId(null);

		for (IngredientDTO ingredient : recipe.getIngredients()) {
			ingredient.setId(null);
		}

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.field", is("instruction")));
	}

	@Test
	public void recipeCreationReturnSuccesstInCaseOfPresenceOfValidBody() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setId(null);

		for (IngredientDTO ingredient : recipe.getIngredients()) {
			ingredient.setId(null);
		}

		for (InstructionDTO instruction : recipe.getInstructions()) {
			instruction.setId(null);
		}

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	public void recipeCreationReturnSuccesstWithCashedResponseInCaseOfDuplicatedRequests() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setId(null);

		for (IngredientDTO ingredient : recipe.getIngredients()) {
			ingredient.setId(null);
		}

		for (InstructionDTO instruction : recipe.getInstructions()) {
			instruction.setId(null);
		}

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		MvcResult result = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		
		int firstId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

		requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		result = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		
		int secondId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
		
		assertEquals(firstId, secondId);
	}
	
	@Test
	public void recipeSearchReturnTheCorrectRecipeWhenAValidIDIsPassed() throws Exception {

		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setId(null);

		for (IngredientDTO ingredient : recipe.getIngredients()) {
			ingredient.setId(null);
		}

		for (InstructionDTO instruction : recipe.getInstructions()) {
			instruction.setId(null);
		}

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		MvcResult result = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		
		int id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
		
		requestBuilder = get("/recipe/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(id)));
	}
	
	@Test
	public void recipeSearchReturnTheNotFoundWhenAnInvalidIDIsPassed() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder = get("/recipe/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
	}
	
	@Test
	public void recipeUpdateReturnTheNotFoundWhenAnInvalidRecipeIDIsPassed() throws Exception {
		
		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();
		
		MockHttpServletRequestBuilder requestBuilder = put("/recipe")
				.content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
	}
	
	@Test
	public void recipeUpdateReturnTheBadRequestWhenAnInvalidIngredientIDIsPassed() throws Exception {
		
		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setId(null);

		for (IngredientDTO ingredient : recipe.getIngredients()) {
			ingredient.setId(null);
		}

		for (InstructionDTO instruction : recipe.getInstructions()) {
			instruction.setId(null);
		}

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		MvcResult result = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		
		recipe = convertJsonToObject(result.getResponse().getContentAsString());
		
		recipe.getIngredients().get(0).setId(33);
		
		requestBuilder = put("/recipe")
				.content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
	}
	
	@Test
	public void recipeUpdateReturnTheBadRequestWhenAnInvalidInstructionIDIsPassed() throws Exception {
		
		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setId(null);

		for (IngredientDTO ingredient : recipe.getIngredients()) {
			ingredient.setId(null);
		}

		for (InstructionDTO instruction : recipe.getInstructions()) {
			instruction.setId(null);
		}

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		MvcResult result = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		
		recipe = convertJsonToObject(result.getResponse().getContentAsString());
		
		recipe.getInstructions().get(0).setId(33);
		
		requestBuilder = put("/recipe")
				.content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
	}
	
	@Test
	public void recipeUpdateReturnTheSuccessWhenAnValidRecipeIsPassed() throws Exception {
		
		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setId(null);

		for (IngredientDTO ingredient : recipe.getIngredients()) {
			ingredient.setId(null);
		}

		for (InstructionDTO instruction : recipe.getInstructions()) {
			instruction.setId(null);
		}

		MockHttpServletRequestBuilder requestBuilder = post("/recipe").content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Idempotency-Key", "1");

		MvcResult result = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		
		recipe = convertJsonToObject(result.getResponse().getContentAsString());
		
		recipe.getInstructions().remove(0);
		
		requestBuilder = put("/recipe")
				.content(convertObjectToJsonString(recipe))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		result = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		
		recipe = convertJsonToObject(result.getResponse().getContentAsString());
		
		assertEquals(3, recipe.getInstructions().size());
	}

	private String convertObjectToJsonString(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private RecipeDTO convertJsonToObject(String json) {
		try {
			return new ObjectMapper().readValue(json, RecipeDTO.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
