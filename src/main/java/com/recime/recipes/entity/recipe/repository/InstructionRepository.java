package com.recime.recipes.entity.recipe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recime.recipes.entity.recipe.model.Instruction;

@Repository
public interface InstructionRepository extends CrudRepository<Instruction, Integer> {

}
