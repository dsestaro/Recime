package com.recime.recipes.entity.recipe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recime.recipes.entity.recipe.model.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

}
