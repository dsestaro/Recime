package com.recime.recipes.entity.idempotency.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Idempotency {

	@Id
	private String key;
	
	@Column
	private String response;
	
	@Column
	private int status;
}
