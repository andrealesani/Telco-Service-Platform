package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.OptionalProduct;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.math.BigDecimal;
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
		OptionalProduct optionalProduct = entityManager.find(OptionalProduct.class, id);
		entityManager.detach(optionalProduct);
		return optionalProduct;
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

	public void createOptionalProduct(String name, BigDecimal monthlyFee) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		OptionalProduct optionalProduct = new OptionalProduct(name, monthlyFee);

		entityManager.getTransaction().begin();
		entityManager.persist(optionalProduct);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
}
