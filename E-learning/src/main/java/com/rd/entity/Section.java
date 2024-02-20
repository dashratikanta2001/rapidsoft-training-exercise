package com.rd.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Section extends BaseEntity{

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
	private String name;
	private int sectionOrder;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@OneToMany(mappedBy = "section")
	private List<Lecture> lectures;

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

	public int getOrder() {
		return sectionOrder;
	}

	public void setOrder(int order) {
		this.sectionOrder = order;
	}

	public Section(Integer id, String name, int order) {
		super();
//		this.id = id;
		this.name = name;
		this.sectionOrder = order;
	}

	public Section() {
		super();
		// TODO Auto-generated constructor stub
	}

}
