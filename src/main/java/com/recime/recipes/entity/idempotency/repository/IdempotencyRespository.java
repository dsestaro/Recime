package com.recime.recipes.entity.idempotency.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recime.recipes.entity.idempotency.model.Idempotency;

@Repository
public interface IdempotencyRespository extends CrudRepository<Idempotency, String> {

}
