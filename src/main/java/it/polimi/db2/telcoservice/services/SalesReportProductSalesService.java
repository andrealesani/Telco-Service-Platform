package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.materialized.SalesReportProductSales;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Stateless
public class SalesReportProductSalesService {
    @PersistenceContext
    private EntityManager entityManager;

    public SalesReportProductSales findBestSeller() {
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
