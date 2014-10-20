package com.training.jpa.service;

import com.training.jpa.model.Order;
import com.training.jpa.model.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShoppingListService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void saveProduct(Product product) {
		entityManager.persist(product);
	}

	@Transactional
	public void saveOrder(Order order) {
		entityManager.persist(order);
	}

	public Product findProduct(int productId) {
		return entityManager.find(Product.class, productId);
	}

	public Order findOrder(int orderId) {
		return entityManager.find(Order.class, orderId);
	}

	public List<Product> findProductsInCategory(String category) {
		return entityManager
				.createQuery("SELECT p FROM Product p WHERE p.category = :category", Product.class)
				.setParameter("category", category)
				.getResultList();
	}

	@Transactional
	public void updatePrice(int productId, int newPrice) {
		Product product = findProduct(productId);
		product.setPrice(newPrice);
	}

	@Transactional
	public void addProductToOrder(int orderId, int productId) {
		Order order = findOrder(orderId);
		Product product = findProduct(productId);

		order.addProduct(product);
	}
}
