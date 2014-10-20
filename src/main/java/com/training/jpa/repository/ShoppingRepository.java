package com.training.jpa.repository;

import com.training.jpa.model.Order;
import com.training.jpa.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ShoppingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findProductsInCategory(String category) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        query.select(productRoot).where(criteriaBuilder.equal(productRoot.get("category"), category));

        return entityManager.createQuery(query).getResultList();
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
