package com.pp.dto;

import java.util.List;

import com.pp.util.SortOrder;

public class PaginationResponseDto<T> {
//	private List<T> items;
//	private long totalItems;
//	private int totalPages;
//	private int currentPage;
//
//	public List<T> getItems() {
//		return items;
//	}
//
//	public void setItems(List<T> items) {
//		this.items = items;
//	}
//
//	public long getTotalItems() {
//		return totalItems;
//	}
//
//	public void setTotalItems(long totalItems) {
//		this.totalItems = totalItems;
//	}
//
//	public int getTotalPages() {
//		return totalPages;
//	}
//
//	public void setTotalPages(int totalPages) {
//		this.totalPages = totalPages;
//	}
//
//	public int getCurrentPage() {
//		return currentPage;
//	}
//
//	public void setCurrentPage(int currentPage) {
//		this.currentPage = currentPage;
//	}
//
//	public PaginationResponseDto(List<T> items, long totalItems, int totalPages, int currentPage) {
//		super();
//		this.items = items;
//		this.totalItems = totalItems;
//		this.totalPages = totalPages;
//		this.currentPage = currentPage;
//	}
//
//	public PaginationResponseDto() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	private List<T> items;
	
	private int pageNumber;
	
	private int numberOfElements;
	
	private int size;
	
	private int totalPages;
	
	private long totalElements;
	
	private SortOrder sort;
	
	private boolean first;
	
	private boolean last;

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public SortOrder getSort() {
		return sort;
	}

	public void setSort(SortOrder sort) {
		this.sort = sort;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public PaginationResponseDto(List<T> items, int pageNumber, int numberOfElements, int size, int totalPages,
			long totalElements, SortOrder sort, boolean first, boolean last) {
		super();
		this.items = items;
		this.pageNumber = pageNumber;
		this.numberOfElements = numberOfElements;
		this.size = size;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.sort = sort;
		this.first = first;
		this.last = last;
	}

	public PaginationResponseDto() {
		super();	
		// TODO Auto-generated constructor stub
	}
	
	
}

































