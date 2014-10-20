package com.training.jpa.service;

import com.training.jpa.model.Order;
import com.training.jpa.model.Product;
import com.training.jpa.model.Tag;
import com.training.jpa.repository.ShoppingRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListService {

    @Inject
    private ShoppingRepository shoppingRepository;
    @Inject
    TagService tagService;

    @Transactional
    public void saveProduct(Product product) {
        List<Tag> tags = new ArrayList<>();
        for (Tag tag : product.getTags()) {
            tag = tagService.saveTag(tag);
            tag.getProducts().add(product);
            tags.add(tag);
        }
        product.getTags().clear();
        product.getTags().addAll(tags);
        shoppingRepository.saveProduct(product);
    }

    @Transactional
    public void saveOrder(Order order) {
        for (Product product : order.getProducts()) {
            saveProduct(product) ;
        }
        shoppingRepository.saveOrder(order);
    }

    public Product findProduct(int productId) {
        return shoppingRepository.findProduct(productId);
    }

    public Order findOrder(int orderId) {
        return shoppingRepository.findOrder(orderId);
    }

    public List<Product> findProductsInCategory(String category) {
        return tagService.getTagByName(category).getProducts();
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
