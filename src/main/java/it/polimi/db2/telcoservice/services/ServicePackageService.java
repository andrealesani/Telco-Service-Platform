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
	@PersistenceContext
	private EntityManager entityManager;

	public List<ServicePackage> findAllServicePackages() {
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
		return entityManager.find(ServicePackage.class, id);
	}

	public ServicePackage createServicePackage(String name, Set<Service> services, Set<ValidityPeriod> validityPeriods, Set<OptionalProduct> optionalProducts) {
		ServicePackage servicePackage = new ServicePackage(name, services, validityPeriods, optionalProducts);
		entityManager.persist(servicePackage);
		return servicePackage;
	}
}
