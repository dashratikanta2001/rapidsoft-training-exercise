package com.rd.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDateTime createdAt;
	
	private LocalDateTime lastmodifiedAt;
	
	private String createdBy;
	
	private String lastModifiedBy;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLastmodifiedAt() {
		return lastmodifiedAt;
	}

	public void setLastmodifiedAt(LocalDateTime lastmodifiedAt) {
		this.lastmodifiedAt = lastmodifiedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public BaseEntity(Integer id, LocalDateTime createdAt, LocalDateTime lastmodifiedAt, String createdBy,
			String lastModifiedBy) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.lastmodifiedAt = lastmodifiedAt;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
	}

	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
