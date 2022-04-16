package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.materialized.SalesReportValidityPackages;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SalesReportValidityPackagesService {
    public List<SalesReportValidityPackages> findAllSalesReports() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<SalesReportValidityPackages> srvpList = new ArrayList<>();
        try {
            srvpList = entityManager.createNamedQuery("SalesReportValidityPackages.findAllSalesReports", SalesReportValidityPackages.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return srvpList;
    }
}
