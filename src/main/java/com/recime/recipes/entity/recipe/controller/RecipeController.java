package com.recime.recipes.entity.recipe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recime.recipes.entity.recipe.dto.RecipeDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
	
	Logger logger = LoggerFactory.getLogger(RecipeController.class);
	
	@PostMapping
    public RecipeDTO createBooking(@Valid @RequestBody RecipeDTO recipe){
		
        return recipe;
    }

}
