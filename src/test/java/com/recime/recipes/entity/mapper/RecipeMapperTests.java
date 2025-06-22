package com.recime.recipes.entity.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.recime.recipes.entity.recipe.dto.IngredientDTO;
import com.recime.recipes.entity.recipe.dto.InstructionDTO;
import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.entity.recipe.mapper.RecipeMapper;
import com.recime.recipes.entity.recipe.model.Ingredient;
import com.recime.recipes.entity.recipe.model.Instruction;
import com.recime.recipes.entity.recipe.model.Recipe;

public class RecipeMapperTests {
	@Test
	void dtoConversionShouldReturnTheCorrectEntity() {

		IngredientDTO ingredientDTO = new IngredientDTO();
		ingredientDTO.setId(1);
		ingredientDTO.setName("Flour");
		ingredientDTO.setQuantity(200);

		InstructionDTO instructionDTO = new InstructionDTO();
		instructionDTO.setId(1);
		instructionDTO.setText("Mix all ingredients.");

		RecipeDTO dto = new RecipeDTO();
		dto.setId(100);
		dto.setTitle("Pancakes");
		dto.setDescription("Delicious homemade pancakes.");
		dto.setServings(4);
		dto.setVegetarian(true);
		dto.setIngredients(Arrays.asList(ingredientDTO));
		dto.setInstructions(Arrays.asList(instructionDTO));

		Recipe entity = RecipeMapper.toEntity(dto);

		assertNotNull(entity);
		assertEquals(100, entity.getId());
		assertEquals("Pancakes", entity.getTitle());
		assertEquals("Delicious homemade pancakes.", entity.getDescription());
		assertEquals(4, entity.getServings());
		assertTrue(entity.isVegetarian());

		List<Ingredient> ingredients = entity.getIngredients();
		assertNotNull(ingredients);
		assertEquals(1, ingredients.size());
		assertEquals(1, ingredients.get(0).getId());
		assertEquals("Flour", ingredients.get(0).getName());
		assertEquals(200, ingredients.get(0).getQuantity());

		List<Instruction> instructions = entity.getIntructions();
		assertNotNull(instructions);
		assertEquals(1, instructions.size());
		assertEquals(1, instructions.get(0).getId());
		assertEquals("Mix all ingredients.", instructions.get(0).getText());
	}

	@Test
	void shouldReturnNullWhenDtoIsNull() {
		Recipe entity = RecipeMapper.toEntity(null);

		assertNull(entity);
	}

	@Test
	void entityConversionShouldReturnTheCorrectDTO() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(1);
		ingredient.setName("Sugar");
		ingredient.setQuantity(100);

		Instruction instruction = new Instruction();
		instruction.setId(1);
		instruction.setText("Add sugar to the mixture.");

		Recipe entity = new Recipe();
		entity.setId(200);
		entity.setTitle("Cake");
		entity.setDescription("Sweet cake recipe.");
		entity.setServings(8);
		entity.setVegetarian(false);
		entity.setIngredients(Arrays.asList(ingredient));
		entity.setIntructions(Arrays.asList(instruction));

		RecipeDTO dto = RecipeMapper.toDto(entity);

		assertNotNull(dto);
		assertEquals(200, dto.getId());
		assertEquals("Cake", dto.getTitle());
		assertEquals("Sweet cake recipe.", dto.getDescription());
		assertEquals(8, dto.getServings());
		assertTrue(!dto.isVegetarian());

		List<IngredientDTO> ingredientDTOs = dto.getIngredients();
		assertNotNull(ingredientDTOs);
		assertEquals(1, ingredientDTOs.size());
		assertEquals(1, ingredientDTOs.get(0).getId());
		assertEquals("Sugar", ingredientDTOs.get(0).getName());
		assertEquals(100, ingredientDTOs.get(0).getQuantity());

		List<InstructionDTO> instructionDTOs = dto.getInstructions();
		assertNotNull(instructionDTOs);
		assertEquals(1, instructionDTOs.size());
		assertEquals(1, instructionDTOs.get(0).getId());
		assertEquals("Add sugar to the mixture.", instructionDTOs.get(0).getText());
	}

	@Test
	void shouldReturnNullWhenEntityIsNull() {
		RecipeDTO dto = RecipeMapper.toDto(null);

		assertNull(dto);
	}
}
