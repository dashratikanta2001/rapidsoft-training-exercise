package com.rd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Lecture extends BaseEntity{

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;
	
	@OneToOne
	@JoinColumn(name = "resource_id")
	private Resource resource;

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lecture(Integer id, String name) {
		super();
//		this.id = id;
		this.name = name;
	}

	public Lecture() {
		super();
		// TODO Auto-generated constructor stub
	}

}
