package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.SubscriptionOrder;
import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.exceptions.CredentialsException;
import it.polimi.db2.telcoservice.exceptions.UserAlreadyExistsException;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class SubscriptionOrderService {
	// @PersistenceContext(unitName = "db2_jpa")
	@PersistenceUnit
	private EntityManagerFactory emf; // not used

	public void createOrder(SubscriptionOrder order) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.persist(order);
	}
}
