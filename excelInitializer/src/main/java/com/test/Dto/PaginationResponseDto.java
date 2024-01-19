package com.test.Dto;

import java.util.List;

public class PaginationResponseDto<T> {
	private List<T> items;
	private long totalItems;
	private int totalPages;
	private int currentPage;

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public PaginationResponseDto(List<T> items, long totalItems, int totalPages, int currentPage) {
		super();
		this.items = items;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}

	public PaginationResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
