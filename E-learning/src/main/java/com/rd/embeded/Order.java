package com.rd.embeded;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "_order")
public class Order {

	@EmbeddedId
	private OrderId id;
	
	private String orederInfo;
	private String anotherField;
	
	
	
}
