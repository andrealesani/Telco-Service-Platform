package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.materialized.SalesReportInsolventUsers;
import it.polimi.db2.telcoservice.entities.materialized.SalesReportSuspendedOrders;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SalesReportSuspendedOrdersService {
    public List<SalesReportSuspendedOrders> findAllSuspended() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<SalesReportSuspendedOrders> srsoList = new ArrayList<>();
        try {
            srsoList = entityManager.createNamedQuery("SalesReportSuspendedOrders.findAllSuspended", SalesReportSuspendedOrders.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return srsoList;
    }
}
