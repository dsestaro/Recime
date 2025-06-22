package com.recime.recipes.entity.recipe.service;

import org.springframework.stereotype.Service;

import com.recime.recipes.entity.recipe.dto.RecipeDTO;
import com.recime.recipes.entity.recipe.exception.InvalidIdException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RecipeService {

	public RecipeDTO create(RecipeDTO recipeDTO) {
		
		if(recipeDTO.getId() != 0) {
			throw new InvalidIdException("recipe");
		}
		
		return recipeDTO;
	}
}
