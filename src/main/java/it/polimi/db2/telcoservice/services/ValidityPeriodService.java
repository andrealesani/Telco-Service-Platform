package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ValidityPeriodService {
	// @PersistenceContext(unitName = "db2_jpa")
	@PersistenceUnit
	private EntityManagerFactory emf; // not used

	public List<ValidityPeriod> findAllValidityPeriods() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<ValidityPeriod> vpList = new ArrayList<>();
		try {
			vpList = entityManager.createNamedQuery("ValidityPeriod.findAllValidityPeriods", ValidityPeriod.class)
					.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return vpList;
	}

	public ValidityPeriod findValidityPeriodById(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ValidityPeriod validityPeriod =  entityManager.find(ValidityPeriod.class, id);
		entityManager.detach(validityPeriod);
		return validityPeriod;
	}

	public int findNumValidityPeriods() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		int numPeriods = 0;
		try {
			numPeriods = ((Number) entityManager.createNamedQuery("ValidityPeriod.findNumValidityPeriods", Integer.class)
					.getSingleResult()).intValue();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return numPeriods;
	}
}
