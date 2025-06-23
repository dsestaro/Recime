package com.recime.recipes.entity.recipe.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.recime.recipes.entity.recipe.model.Ingredient;
import com.recime.recipes.entity.recipe.model.Instruction;
import com.recime.recipes.entity.recipe.model.Recipe;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class RecipeSpecification {

	private static final String VEGETARIAN = "vegetarian";
	private static final String SERVINGS = "servings";
	private static final String INGREDIENTS = "ingredients";
	private static final String INGREDIENTS_NAME = "name";
	private static final String INSTRUCTIONS = "instructions";
	private static final String INSTRUCTIONS_TEXT = "text";
	private static final String ID = "id";

	public static Specification<Recipe> isVegetarian(Boolean vegetarian) {
		return (root, query, criteriaBuilder) -> {

			if (vegetarian == null) {
				return null;
			}

			return criteriaBuilder.equal(root.get(VEGETARIAN), vegetarian);
		};
	}

	public static Specification<Recipe> servingsQuantity(Integer servings) {
		return (root, query, criteriaBuilder) -> {

			if (servings == null) {
				return null;
			}

			return criteriaBuilder.equal(root.get(SERVINGS), servings);
		};
	}

	public static Specification<Recipe> includesIngredients(List<String> ingredients) {
		return (root, query, criteriaBuilder) -> {

			if (ingredients == null || ingredients.isEmpty()) {
				return null;
			}

			query.distinct(true);

			Join<Object, Ingredient> join = root.join(INGREDIENTS, JoinType.INNER);

			return join.get(INGREDIENTS_NAME).in(ingredients);
		};
	}

	public static Specification<Recipe> excludesIngredients(List<String> ingredients) {
		return (root, query, criteriaBuilder) -> {

			if (ingredients == null || ingredients.isEmpty()) {
				return null;
			}

			Subquery<Long> subquery = query.subquery(Long.class);
			Root<Recipe> subRoot = subquery.from(Recipe.class);
			Join<Object, Ingredient> ingredientJoin = subRoot.join(INGREDIENTS);

			subquery.select(criteriaBuilder.literal(1L))
					.where(criteriaBuilder.and(criteriaBuilder.equal(subRoot.get(ID), root.get(ID)),
							ingredientJoin.get(INGREDIENTS_NAME).in(ingredients)));

			return criteriaBuilder.not(criteriaBuilder.exists(subquery));
		};
	}

	public static Specification<Recipe> instructionContains(String text) {
		return (root, query, criteriaBuilder) -> {

			if (text == null || text.isBlank()) {
				return null;
			}

			query.distinct(true);

			Join<Object, Instruction> join = root.join(INSTRUCTIONS, JoinType.INNER);

			return criteriaBuilder.like(criteriaBuilder.lower(join.get(INSTRUCTIONS_TEXT)),
					"%" + text.toLowerCase() + "%");
		};
	}
}
