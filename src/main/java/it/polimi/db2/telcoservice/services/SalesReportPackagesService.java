package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.materialized.SalesReportPackages;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SalesReportPackagesService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<SalesReportPackages> findAllSalesReports() {
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
