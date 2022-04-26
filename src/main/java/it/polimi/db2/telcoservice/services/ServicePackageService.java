package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.exceptions.CredentialsException;
import it.polimi.db2.telcoservice.exceptions.UserAlreadyExistsException;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
		ServicePackage servicePackage = entityManager.find(ServicePackage.class, id);
		entityManager.detach(servicePackage);
		return servicePackage;
	}

	public void createServicePackage(String name, Set<Service> services, Set<ValidityPeriod> validityPeriods, Set<OptionalProduct> optionalProducts) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		ServicePackage servicePackage = new ServicePackage(name, services, validityPeriods, optionalProducts);

		entityManager.getTransaction().begin();
		entityManager.persist(servicePackage);
		entityManager.getTransaction().commit();
		entityManagerFactory.close();
	}
}
