package com.training.jpa.service;

import com.training.jpa.model.Client;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author Jakub Kubrynski
 */
@Service
public class ClientService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void saveClient(Client client) {
		entityManager.persist(client);
	}

	public Client findClient(int clientId) {
		return entityManager.find(Client.class, clientId);
	}
}
