package com.training.jpa.service;

import com.training.jpa.model.Order;
import com.training.jpa.model.Product;
import com.training.jpa.repository.ShoppingRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShoppingListService {

    @Inject
    private ShoppingRepository shoppingRepository;

    @Transactional
    public void saveProduct(Product product) {
        shoppingRepository.saveProduct(product);
    }

    @Transactional
    public void saveOrder(Order order) {
        shoppingRepository.saveOrder(order);
    }

    public Product findProduct(int productId) {
        return shoppingRepository.findProduct(productId);
    }

    public Order findOrder(int orderId) {
        return shoppingRepository.findOrder(orderId);
    }

    public List<Product> findProductsInCategory(String category) {
        return shoppingRepository.findProductsInCategory(category);
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
