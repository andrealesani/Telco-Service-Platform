package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.exceptions.CredentialsException;
import it.polimi.db2.telcoservice.exceptions.UserAlreadyExistsException;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ServicePackageService {
	// @PersistenceContext(unitName = "db2_jpa")
	@PersistenceUnit
	private EntityManagerFactory emf; // not used

	public List<ServicePackage> findAllServicePackages() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<ServicePackage> spList = new ArrayList<>();
		try {
			spList = entityManager.createNamedQuery("ServicePackage.findAllServicePackages", ServicePackage.class)
					.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return spList;
	}

	public ServicePackage findServicePackageById(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<ServicePackage> spList = new ArrayList<>();
		try {
			spList = entityManager.createNamedQuery("ServicePackage.findServicePackageById", ServicePackage.class).setParameter(1, id)
					.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		if (spList.isEmpty())
			return null;
		else if (spList.size() == 1)
			return spList.get(0);
		throw new NonUniqueResultException("There are more than 1 service package with this ID");
	}
}
