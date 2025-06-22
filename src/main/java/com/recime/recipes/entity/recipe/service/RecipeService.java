package com.recime.recipes.entity.recipe.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recime.recipes.entity.recipe.dto.IngredientDTO;
import com.recime.recipes.entity.recipe.dto.InstructionDTO;
import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.entity.recipe.exception.InvalidIdException;
import com.recime.recipes.entity.recipe.exception.RecipeNotFoundException;
import com.recime.recipes.entity.recipe.mapper.RecipeMapper;
import com.recime.recipes.entity.recipe.model.Recipe;
import com.recime.recipes.entity.recipe.repository.RecipeRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RecipeService {

	private RecipeRepository recipeRepository;
	
	public RecipeDTO create(RecipeDTO recipeDTO) {
		
		log.debug("Creating new recipe with title {}.", recipeDTO.getTitle());
		
		if(recipeDTO.getId() != null && recipeDTO.getId() != 0) {
			log.error("Recipe ID found when trying to create a new recipe");
			throw new InvalidIdException("recipe");
		}
		
		for(IngredientDTO ingredient : recipeDTO.getIngredients()) {
			if(ingredient.getId() != null && ingredient.getId() != 0) {
				log.error("Ingredient ID found when trying to create a new recipe");
				throw new InvalidIdException("ingredient");
			}
		}
		
		for(InstructionDTO instruction : recipeDTO.getInstructions()) {
			if(instruction.getId() != null && instruction.getId() != 0) {
				log.error("Instruction ID found when trying to create a new recipe");
				throw new InvalidIdException("instruction");
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
		
		if(recipe.isEmpty()) {
			log.debug("Recipe with ID {} not found.", id);
			
			throw new RecipeNotFoundException();
		}
		
		log.debug("Recipe with ID {} found.", id);
		
		return RecipeMapper.toDto(recipe.get());
	}
}
