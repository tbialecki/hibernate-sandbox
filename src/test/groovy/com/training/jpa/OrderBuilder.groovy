package com.training.jpa

import com.training.jpa.model.Order
import com.training.jpa.model.Product

/**
 * @author Jakub Kubrynski
 */
class OrderBuilder {
	Product[] products = []

	static OrderBuilder anOrder() {
		new OrderBuilder()
	}

	OrderBuilder withProduct(Product product) {
		products += product
		this
	}

	Order build() {
		def order = new Order()
		products.each {
			order.addProduct(it)
		}
		order
	}
}
