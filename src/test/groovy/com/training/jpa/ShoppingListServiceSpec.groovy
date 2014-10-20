package com.training.jpa

import com.training.jpa.config.DatabaseConfig
import com.training.jpa.model.Order
import com.training.jpa.model.Product
import com.training.jpa.service.ShoppingListService
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.inject.Inject
import javax.transaction.Transactional

@ContextConfiguration(classes = DatabaseConfig)
class ShoppingListServiceSpec extends Specification {

	@Inject
	ShoppingListService sut

	def "should save product into database"() {
		when:
			def product = new Product("beer", "drinks", 3)
			sut.saveProduct(product)

		then:
			def productFromDB = sut.findProduct(product.id)
			productFromDB
			productFromDB.name == "beer"
			productFromDB.category == "drinks"
			productFromDB.price == 3
	}

	def "should find all products from category"() {
		given:
			sut.saveProduct(new Product("bread", "breadstuff", 2))
			sut.saveProduct(new Product("bun", "breadstuff", 1))
			sut.saveProduct(new Product("ham", "meat", 10))

		when:
			List<Product> products = sut.findProductsInCategory("breadstuff")

		then:
			products
			products.size() == 2
			products.each {
				assert it.category == "breadstuff"
			}
	}

	def "should update product price"() {
		given:
			def beer = new Product("beer", "drinks", 3)
			sut.saveProduct(beer)
			def wine = new Product("wine", "drinks", 20)
			sut.saveProduct(wine)

		when:
			sut.updatePrice(beer.id, 5)

		then:
			sut.findProduct(beer.id)?.price == 5
			sut.findProduct(wine.id)?.price == 20
	}

	@Transactional
	def "should add product to order"() {
		given:
			def beer = new Product("beer", "drinks", 3)
			sut.saveProduct(beer)
			def order = new Order()
			sut.saveOrder(order)

		when:
			sut.addProductToOrder(order.id, beer.id)

		then:
			def orderFromDb = sut.findOrder(order.id)
			orderFromDb.products.id == [beer.id]
	}
}
