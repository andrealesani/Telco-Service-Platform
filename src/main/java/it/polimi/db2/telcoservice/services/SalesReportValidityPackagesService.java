package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.materialized.SalesReportValidityPackages;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SalesReportValidityPackagesService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<SalesReportValidityPackages> findAllSalesReports() {
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
