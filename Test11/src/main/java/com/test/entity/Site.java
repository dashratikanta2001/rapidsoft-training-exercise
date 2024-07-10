package com.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Site {

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
