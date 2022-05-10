package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class ServicePackageService {
    @PersistenceContext
    private EntityManager entityManager;
    @EJB(name = "it.polimi.db2.telcoservice.services/ServiceService")
    private ServiceService sService;
    @EJB(name = "it.polimi.db2.telcoservice.services/ValidityPeriodService")
    private ValidityPeriodService vpService;
    @EJB(name = "it.polimi.db2.telcoservice.services/OptionalProductService")
    private OptionalProductService opService;

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

    public ServicePackage createServicePackage(String name, List<Integer> serviceIDs, List<Integer> validityPeriodIDs, List<Integer> optionalProductIDs) {

        Set<Service> services = new HashSet<>();
        for (int id : serviceIDs) {
            services.add(sService.findServiceById(id));
        }

        Set<ValidityPeriod> validityPeriods = new HashSet<>();
        for (int id : validityPeriodIDs) {
            validityPeriods.add(vpService.findValidityPeriodById(id));
        }

        Set<OptionalProduct> optionalProducts = new HashSet<>();
        for (int id : optionalProductIDs) {
            optionalProducts.add(opService.findOptionalProductById(id));
        }

        ServicePackage servicePackage = new ServicePackage(name, services, validityPeriods, optionalProducts);

        entityManager.persist(servicePackage);

        return servicePackage;
    }
}
