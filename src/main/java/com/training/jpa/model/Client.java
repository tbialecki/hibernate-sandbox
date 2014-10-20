package com.training.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jakub Kubrynski
 */
@Entity
public class Client extends AbstractEntity {

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn
	private Collection<Order> orders = new ArrayList<Order>();

	public void addOrder(Order order) {
		orders.add(order);
	}

	public Collection<Order> getOrders() {
		return orders;
	}
}
