package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.OptionalProduct;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OptionalProductService {
	@PersistenceUnit
	private EntityManagerFactory emf; // not used

	public List<OptionalProduct> findAllOptionalProducts() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<OptionalProduct> opList = new ArrayList<>();
		try {
			opList = entityManager.createNamedQuery("OptionalProduct.findAllOptionalProducts", OptionalProduct.class)
					.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return opList;
	}

	public OptionalProduct findOptionalProductById(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.find(OptionalProduct.class, id);
	}

	public int findNumOptionalProducts() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		int numProducts = 0;
		try {
			numProducts = ((Number) entityManager.createNamedQuery("OptionalProduct.findNumOptionalProducts", Integer.class)
					.getSingleResult()).intValue();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return numProducts;
	}
}
