package com.recime.recipes.entity.recipe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.recime.recipes.entity.recipe.dto.IngredientDTO;
import com.recime.recipes.entity.recipe.dto.InstructionDTO;
import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.entity.recipe.exception.InvalidIdException;
import com.recime.recipes.entity.recipe.exception.RecipeNotFoundException;
import com.recime.recipes.entity.recipe.model.Recipe;
import com.recime.recipes.entity.recipe.repository.IngredientRepository;
import com.recime.recipes.entity.recipe.repository.InstructionRepository;
import com.recime.recipes.entity.recipe.repository.RecipeRepository;
import com.recime.recipes.utils.RecipeDTOGenerator;
import com.recime.recipes.utils.RecipeGenerator;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTests {

	@Mock
	private RecipeRepository recipeRepository;

	@Mock
	private IngredientRepository ingredientRepository;
	
	@Mock
	private InstructionRepository instructionRepository;
	
	private RecipeService recipeService;

	@BeforeEach
	public void configure() {
		this.recipeService = new RecipeService(recipeRepository, ingredientRepository, instructionRepository);
	}

	@Test
	public void recipeCreationReturnErrorWhenUsingADTOWithARecipeId() {
		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		assertThrows(InvalidIdException.class, () -> this.recipeService.create(recipe));
	}

	@Test
	public void recipeCreationReturnErrorWhenUsingADTOWithAnIngredientId() {
		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setId(null);

		assertThrows(InvalidIdException.class, () -> this.recipeService.create(recipe));
	}

	@Test
	public void recipeCreationReturnErrorWhenUsingADTOWithAnInstructionId() {
		RecipeDTO recipe = RecipeDTOGenerator.populateRecipeDTO();

		recipe.setId(null);

		for (IngredientDTO ingredient : recipe.getIngredients()) {
			ingredient.setId(null);
		}

		assertThrows(InvalidIdException.class, () -> this.recipeService.create(recipe));
	}

	@Test
	public void recipeCreationReturnAValidResponseWhenReceivingAValidObject() {

		RecipeDTO input = RecipeDTOGenerator.populateRecipeDTO();

		input.setId(null);

		for (IngredientDTO ingredient : input.getIngredients()) {
			ingredient.setId(null);
		}

		for (InstructionDTO instruction : input.getInstructions()) {
			instruction.setId(null);
		}

		doAnswer((Answer<Recipe>) invocation -> {
			return RecipeGenerator.populateRecipe();
		}).when(this.recipeRepository).save(any());

		RecipeDTO recipe = this.recipeService.create(input);

		assertNotNull(recipe);
		assertEquals(10, recipe.getId());
		assertEquals("Title", recipe.getTitle());
		assertEquals("Description", recipe.getDescription());
		assertEquals(2, recipe.getServings());
		assertTrue(recipe.isVegetarian());

		List<IngredientDTO> ingredientDTOs = recipe.getIngredients();
		assertNotNull(ingredientDTOs);
		assertEquals(2, ingredientDTOs.size());

		List<InstructionDTO> instructionDTOs = recipe.getInstructions();
		assertNotNull(instructionDTOs);
		assertEquals(4, instructionDTOs.size());
	}

	@Test
	public void recipeSearchByIdReturnAnErrorWhenUsingAnInvalidId() {

		doAnswer((Answer<Optional<Recipe>>) invocation -> {

			Recipe recipe = null;

			return Optional.ofNullable(recipe);
		}).when(this.recipeRepository).findById(any());

		assertThrows(RecipeNotFoundException.class, () -> this.recipeService.findById(1));
	}
	
	@Test
	public void recipeSearchByIdReturnAValidResponseWhenAnExistingIDIsPassed() {

		doAnswer((Answer<Optional<Recipe>>) invocation -> {

			Recipe recipe = RecipeGenerator.populateRecipe();

			return Optional.ofNullable(recipe);
		}).when(this.recipeRepository).findById(any());

		RecipeDTO recipe = this.recipeService.findById(1);
		
		assertNotNull(recipe);
		assertEquals(10, recipe.getId());
		assertEquals("Title", recipe.getTitle());
		assertEquals("Description", recipe.getDescription());
		assertEquals(2, recipe.getServings());
		assertTrue(recipe.isVegetarian());

		List<IngredientDTO> ingredientDTOs = recipe.getIngredients();
		assertNotNull(ingredientDTOs);
		assertEquals(2, ingredientDTOs.size());

		List<InstructionDTO> instructionDTOs = recipe.getInstructions();
		assertNotNull(instructionDTOs);
		assertEquals(4, instructionDTOs.size());
	}
}
