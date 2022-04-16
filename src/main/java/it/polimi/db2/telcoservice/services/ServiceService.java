package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.Service;
import it.polimi.db2.telcoservice.entities.ServicePackage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

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
}
