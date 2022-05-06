package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.exceptions.CredentialsException;
import it.polimi.db2.telcoservice.exceptions.UserAlreadyExistsException;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
public class SubscriptionOrderService {
	@PersistenceContext
	private EntityManager entityManager;

	public SubscriptionOrder findSubscriptionOrderById(int id) {
		return entityManager.find(SubscriptionOrder.class, id);
	}

	public List<SubscriptionOrder> findOrdersByUser(int userId) {
		List<SubscriptionOrder> soList = new ArrayList<>();
		try {
			soList = entityManager.createNamedQuery("SubscriptionOrder.findSubscriptionOrdersByUser", SubscriptionOrder.class)
					.setParameter(1, userId)
					.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return soList;
	}

	public void makePayment(int id, boolean valid, User user) {
		SubscriptionOrder order = entityManager.find(SubscriptionOrder.class, id);
		order.setValid(valid);
		order.setUser(user);
		entityManager.flush();
	}

	public SubscriptionOrder createOrder(ServicePackage servicePackage, ValidityPeriod validityPeriod, Set<OptionalProduct> optionalProducts, Timestamp creationTs) {
		SubscriptionOrder order = new SubscriptionOrder(servicePackage, validityPeriod, optionalProducts, creationTs);
		entityManager.persist(order);
		return order;
	}
}
