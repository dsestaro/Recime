package com.recime.recipes.entity.recipe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recime.recipes.entity.recipe.dto.IngredientDTO;
import com.recime.recipes.entity.recipe.dto.InstructionDTO;
import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.entity.recipe.exception.IngredientNotFoundException;
import com.recime.recipes.entity.recipe.exception.InstructionNotFoundException;
import com.recime.recipes.entity.recipe.exception.InvalidIdException;
import com.recime.recipes.entity.recipe.exception.InvalidIdLinkException;
import com.recime.recipes.entity.recipe.exception.RecipeNotFoundException;
import com.recime.recipes.entity.recipe.mapper.RecipeMapper;
import com.recime.recipes.entity.recipe.model.Ingredient;
import com.recime.recipes.entity.recipe.model.Instruction;
import com.recime.recipes.entity.recipe.model.Recipe;
import com.recime.recipes.entity.recipe.repository.IngredientRepository;
import com.recime.recipes.entity.recipe.repository.InstructionRepository;
import com.recime.recipes.entity.recipe.repository.RecipeRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RecipeService {

	private RecipeRepository recipeRepository;
	private IngredientRepository ingredientRepository;
	private InstructionRepository instructionRepository;
	
	public RecipeDTO create(RecipeDTO recipeDTO) {

		log.debug("Creating new recipe with title {}.", recipeDTO.getTitle());

		if (recipeDTO.getId() != null && recipeDTO.getId() != 0) {
			log.error("Recipe ID found when trying to create a new recipe");
			throw new InvalidIdException("recipe", recipeDTO.getId());
		}

		for (IngredientDTO ingredient : recipeDTO.getIngredients()) {
			if (ingredient.getId() != null && ingredient.getId() != 0) {
				log.error("Ingredient ID found when trying to create a new recipe");
				throw new InvalidIdException("ingredient", ingredient.getId());
			}
		}

		for (InstructionDTO instruction : recipeDTO.getInstructions()) {
			if (instruction.getId() != null && instruction.getId() != 0) {
				log.error("Instruction ID found when trying to create a new recipe");
				throw new InvalidIdException("instruction", instruction.getId());
			}
		}

		Recipe recipe = RecipeMapper.toEntity(recipeDTO);

		recipe = this.recipeRepository.save(recipe);

		log.debug("Recipe with title {} create successfully.", recipeDTO.getTitle());

		return RecipeMapper.toDto(recipe);
	}

	public RecipeDTO findById(Integer id) {

		log.debug("Retrieving recipe with ID {}.", id);

		Optional<Recipe> recipe = this.recipeRepository.findById(id);

		if (recipe.isEmpty()) {
			log.debug("Recipe with ID {} not found.", id);

			throw new RecipeNotFoundException(String.format("Recipe with ID %d not found.", id));
		}

		log.debug("Recipe with ID {} found.", id);

		return RecipeMapper.toDto(recipe.get());
	}

	public RecipeDTO update(RecipeDTO recipeDTO) {

		if (recipeDTO.getId() == null || recipeDTO.getId() == 0) {
			log.error("Recipe ID not found when trying to update a recipe");
			throw new InvalidIdException("recipe");
		}

		Optional<Recipe> existingRecipe = this.recipeRepository.findById(recipeDTO.getId());

		if (existingRecipe.isEmpty()) {
			log.debug("Recipe with ID {} not found.", recipeDTO.getId());

			throw new RecipeNotFoundException(String.format("Recipe with ID %d not found.", recipeDTO.getId()));
		}

		existingRecipe.get().setTitle(recipeDTO.getTitle());
		existingRecipe.get().setDescription(recipeDTO.getDescription());
		existingRecipe.get().setServings(recipeDTO.getServings());
		existingRecipe.get().setVegetarian(recipeDTO.isVegetarian());

		List<Ingredient> updatedIngredients = new ArrayList<>();

		for (IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {

			if (ingredientDTO.getId() != null) {
				Optional<Ingredient> ingredient;

				ingredient = this.ingredientRepository.findById(ingredientDTO.getId());

				if (ingredient.isEmpty()) {
					log.debug("Ingredient with ID {} not found.", ingredientDTO.getId());

					throw new IngredientNotFoundException(
							String.format("Ingredient with ID %d not found.", ingredientDTO.getId()));
				}

				if (!existingRecipe.get().getIngredients().contains(ingredient.get())) {
					throw new InvalidIdLinkException("ingredient", ingredientDTO.getId());
				}
			}

			updatedIngredients.add(RecipeMapper.ingredientDtoToEntity(ingredientDTO));
		}

		existingRecipe.get().getIngredients().clear();
		existingRecipe.get().getIngredients().addAll(updatedIngredients);
		
		List<Instruction> updatedInstructions = new ArrayList<>();
		
		for (InstructionDTO instructionDTO : recipeDTO.getInstructions()) {

			if (instructionDTO.getId() != null) {
				Optional<Instruction> instruction = this.instructionRepository.findById(instructionDTO.getId());

				if (instruction.isEmpty()) {
					log.debug("Instruction with ID {} not found.", instructionDTO.getId());

					throw new InstructionNotFoundException(
							String.format("Instruction with ID %d not found.", instructionDTO.getId()));
				}

				if (!existingRecipe.get().getInstructions().contains(instruction.get())) {
					throw new InvalidIdLinkException("instruction", instructionDTO.getId());
				}

			updatedInstructions.add(RecipeMapper.instructionDtoToEntity(instructionDTO));
			
			}
		}

		existingRecipe.get().getInstructions().clear();
		existingRecipe.get().getInstructions().addAll(updatedInstructions);

		return RecipeMapper.toDto(this.recipeRepository.save(existingRecipe.get()));
	}
}
