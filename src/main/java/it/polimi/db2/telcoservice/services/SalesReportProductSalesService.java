package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.materialized.SalesReportProductSales;
import it.polimi.db2.telcoservice.entities.materialized.SalesReportSuspendedOrders;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

@Stateless
public class SalesReportProductSalesService {
    public SalesReportProductSales findBestSeller() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        SalesReportProductSales srps = null;
        try {
            srps = entityManager.createNamedQuery("SalesReportProductSales.findBestSeller", SalesReportProductSales.class)
                    .getResultStream().findFirst().orElse(null);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return srps;
    }
}
