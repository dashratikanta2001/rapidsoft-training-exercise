package com.rd.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Course extends BaseEntity{

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
	private String name;
	private String description;
	
	@ManyToMany
	@JoinTable(
			name = "authors_courses",
			joinColumns = {
					@JoinColumn(name="course_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name="author_id")
			}
			)
	private List<Author> authors;
	
	@OneToMany(mappedBy = "course")
	private List<Section> sections;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Course(Integer id, String name, String description) {
		super();
//		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

}
