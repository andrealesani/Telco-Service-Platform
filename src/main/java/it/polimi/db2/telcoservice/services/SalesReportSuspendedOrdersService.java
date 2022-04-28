package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.materialized.SalesReportInsolventUsers;
import it.polimi.db2.telcoservice.entities.materialized.SalesReportSuspendedOrders;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SalesReportSuspendedOrdersService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<SalesReportSuspendedOrders> findAllSuspended() {
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
