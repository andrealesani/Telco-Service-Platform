package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.exceptions.CredentialsException;
import it.polimi.db2.telcoservice.exceptions.UserAlreadyExistsException;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Stateless
public class SubscriptionOrderService {
	// @PersistenceContext(unitName = "db2_jpa")
	@PersistenceUnit
	private EntityManagerFactory emf; // not used

	public SubscriptionOrder findSubscriptionOrderById(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		SubscriptionOrder order = entityManager.find(SubscriptionOrder.class, id);
		entityManager.detach(order);

		System.out.println(order.getOptionalProducts().size());

		return order;
	}

	public void makePayment(int id, boolean valid, User user) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		SubscriptionOrder order = entityManager.find(SubscriptionOrder.class, id);
		order.setValid(valid);
		order.setUser(user);
		entityManager.flush();
		entityManager.detach(order);
        entityManager.getTransaction().commit();
	}

	public SubscriptionOrder createOrder(ServicePackage servicePackage, ValidityPeriod validityPeriod, Set<OptionalProduct> optionalProducts, Timestamp creationTs) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		SubscriptionOrder order = new SubscriptionOrder(servicePackage, validityPeriod, optionalProducts, creationTs);

		entityManager.getTransaction().begin();
		entityManager.persist(order);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();

		return order;
	}
}
