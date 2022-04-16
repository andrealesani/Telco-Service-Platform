package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.materialized.SalesReportPackages;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SalesReportPackagesService {
    public List<SalesReportPackages> findAllSalesReports() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<SalesReportPackages> srpList = new ArrayList<>();
        try {
            srpList = entityManager.createNamedQuery("SalesReportPackages.findAllSalesReports", SalesReportPackages.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return srpList;
    }
}
