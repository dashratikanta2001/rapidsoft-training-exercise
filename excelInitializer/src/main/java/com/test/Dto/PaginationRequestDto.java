package com.test.Dto;

import com.test.util.SortOrder;

public class PaginationRequestDto {

	private int page;
	private int size;
	private SortOrder sortOrder;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public SortOrder getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	public PaginationRequestDto(int page, int size, SortOrder sortOrder) {
		super();
		this.page = page;
		this.size = size;
		this.sortOrder = sortOrder;
	}
	public PaginationRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
