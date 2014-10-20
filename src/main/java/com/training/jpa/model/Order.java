package com.training.jpa.model;

import org.hibernate.annotations.Cache;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jakub Kubrynski
 */
@Entity
@Table(name = "Orders") // usually 'order' is a database keyword :)
@Cacheable
public class Order extends AbstractEntity {

	@OneToMany
	@JoinColumn
	private Collection<Product> products = new ArrayList<Product>();

	public void addProduct(Product product) {
		products.add(product);
	}

	public Collection<Product> getProducts() {
		return products;
	}
}
