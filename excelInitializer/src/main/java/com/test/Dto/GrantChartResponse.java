package com.test.Dto;

import java.util.List;
import java.util.Map;

public class GrantChartResponse {

	private String id;
	private String code;
	private String name;
	private String type;
	private String start;
	private String end;
	private int progress;
	private int displayOrder;
	private List<String> dependencies;
	private String project;
	private Map<String, String> styles;

	// Getters and Setters

	// Constructor
	public GrantChartResponse(String id, String code, String name, String type, String start, String end, int progress,
			int displayOrder, List<String> dependencies, String project, Map<String, String> styles) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
		this.start = start;
		this.end = end;
		this.progress = progress;
		this.displayOrder = displayOrder;
		this.dependencies = dependencies;
		this.project = project;
		this.styles = styles;
	}

	

	public GrantChartResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Map<String, String> getStyles() {
		return styles;
	}

	public void setStyles(Map<String, String> styles) {
		this.styles = styles;
	}

}