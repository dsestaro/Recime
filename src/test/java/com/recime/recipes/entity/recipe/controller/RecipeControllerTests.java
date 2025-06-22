package com.recime.recipes.entity.recipe.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recime.recipes.entity.recipe.dto.IngredientDTO;
import com.recime.recipes.entity.recipe.dto.InstructionDTO;
import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.entity.recipe.model.ServingUnitEnum;

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
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfNoBody() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest());
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingTitle() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.setTitle(null);
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("title")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyTitle() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.setTitle("");
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("title")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingDescription() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.setDescription(null);
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("description")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyDescription() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.setDescription("");
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("description")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingInstructions() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.setInstructions(null);
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("instructions")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyInstructions() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.setInstructions(new ArrayList<InstructionDTO>());
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("instructions")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingInstructionText() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.getInstructions().get(0).setText(null);
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("instructions[0].text")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyInstructionsText() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.getInstructions().get(0).setText("");
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("instructions[0].text")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingIngredients() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.setIngredients(null);
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("ingredients")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyIngredients() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.setIngredients(new ArrayList<IngredientDTO>());
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("ingredients")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingIngredientName() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.getIngredients().get(0).setName(null);
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("ingredients[0].name")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfEmptyIngredientName() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.getIngredients().get(0).setName("");
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("ingredients[0].name")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingIngredientQuantity() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.getIngredients().get(0).setQuantity(0);
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("ingredients[0].quantity")));
	}
	
	@Test
	public void recipeCreationReturnBadRequestInCaseOfMissingIngredientUnit() throws Exception {
		
		RecipeDTO recipe = populateRecipeDTO();
		
		recipe.getIngredients().get(0).setUnit(null);
		
		MockHttpServletRequestBuilder requestBuilder = post("/recipe")
                .content(convertObjectToJsonString(recipe))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", "1");
		
		this.mockMvc.perform(requestBuilder)
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$[0].field", is("ingredients[0].unit")));
	}

	private String convertObjectToJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
	
	private RecipeDTO populateRecipeDTO() {
		RecipeDTO recipeDTO = new RecipeDTO();
		
		recipeDTO.setId(10);
		recipeDTO.setTitle("Title");
		recipeDTO.setDescription("Description");
		recipeDTO.setVegetarian(true);
		recipeDTO.setServings(2);
		
		List<IngredientDTO> ingredients = new ArrayList<IngredientDTO>();
		
		IngredientDTO ingredientDTO = new IngredientDTO();
		ingredientDTO.setId(10);
		ingredientDTO.setName("Ingredient 1");
		ingredientDTO.setQuantity(200);
		ingredientDTO.setUnit(ServingUnitEnum.LITER);
		
		ingredients.add(ingredientDTO);
		
		ingredientDTO = new IngredientDTO();
		ingredientDTO.setId(11);
		ingredientDTO.setName("Ingredient 2");
		ingredientDTO.setQuantity(200);
		ingredientDTO.setUnit(ServingUnitEnum.PIECE);
		
		ingredients.add(ingredientDTO);
		
		recipeDTO.setIngredients(ingredients);
		
		List<InstructionDTO> instructions = new ArrayList<InstructionDTO>();
		
		for(int i = 0; i < 4; i++) {
			InstructionDTO instructionDTO = new InstructionDTO();
			
			instructionDTO.setId(i + 20);
			instructionDTO.setText("Instrucion " + i);
			
			instructions.add(instructionDTO);
		}
		
		recipeDTO.setInstructions(instructions);
		
		return recipeDTO;
	}
}
