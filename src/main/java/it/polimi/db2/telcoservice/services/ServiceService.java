package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.OptionalProduct;
import it.polimi.db2.telcoservice.entities.Service;
import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;
import it.polimi.db2.telcoservice.entities.materialized.SalesReportProductSales;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
public class ServiceService {
    public List<Service> findAllServices() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Service> sList = new ArrayList<>();
        try {
            sList = entityManager.createNamedQuery("Service.findAllServices", Service.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return sList;
    }

    public Service findServiceById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Service service = entityManager.find(Service.class, id);
        entityManager.detach(service);
        return service;
    }

    public int findNumServices() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int numServices = 0;
        try {
            numServices = ((Number) entityManager.createNamedQuery("Service.findNumServices", Integer.class)
                    .getSingleResult()).intValue();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return numServices;
    }
}
