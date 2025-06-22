package com.recime.recipes.entity.recipe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recime.recipes.entity.recipe.model.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

}
