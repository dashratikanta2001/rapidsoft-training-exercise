package com.test.util;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SortOrder {

	 ASC ,DESC;
	
	
	 @JsonCreator
	    public static SortOrder fromString(String value) {
	        for (SortOrder sortOrder : SortOrder.values()) {
//	            if (sortOrder.name().equalsIgnoreCase(value)) {
//	                return sortOrder;
//	            }
	            
	            if (sortOrder.name().startsWith(value.toLowerCase())) {
					return sortOrder;
				}
	            if (value.toUpperCase().startsWith(sortOrder.name())) {
	            	return sortOrder;
	            }
	        }
	        throw new RuntimeException("Invalid SortOrder: " + value);
	    }

}
