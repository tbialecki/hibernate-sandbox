package com.training.jpa.repository;

import com.training.jpa.model.Order;
import com.training.jpa.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ShoppingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findProductsInCategory(String category) {
        return entityManager
                .createQuery("SELECT p FROM Product p WHERE p.category = :category", Product.class)
                .setParameter("category", category)
                .getResultList();
    }

    public void saveProduct(Product product) {
        entityManager.persist(product);
    }

    public void saveOrder(Order order) {
        entityManager.persist(order);
    }

    public Product findProduct(int productId) {
        return entityManager.find(Product.class, productId);
    }

    public Order findOrder(int orderId) {
        return entityManager.find(Order.class, orderId);
    }
}
