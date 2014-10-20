package com.training.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product extends AbstractEntity {

	private String name;
	private String category;
	private int price;

	@SuppressWarnings("unused") // required by Hibernate
	private Product() {
	}

	public Product(String name, String category, int price) {
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
