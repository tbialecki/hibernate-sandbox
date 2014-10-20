package com.training.jpa.service;

import com.training.jpa.model.Client;
import com.training.jpa.model.Order;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author Jakub Kubrynski
 */
@Service
public class ClientService {

    @Inject
    private ShoppingListService shoppingListService;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveClient(Client client) {
        for (Order order : client.getOrders()) {
            shoppingListService.saveOrder(order);
        }
        entityManager.persist(client);
    }

    public Client findClient(int clientId) {
        return entityManager.find(Client.class, clientId);
    }
}
