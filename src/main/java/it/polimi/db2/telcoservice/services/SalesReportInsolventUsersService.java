package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.materialized.SalesReportInsolventUsers;
import it.polimi.db2.telcoservice.entities.materialized.SalesReportValidityPackages;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SalesReportInsolventUsersService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<SalesReportInsolventUsers> findAllInsolvent() {
        List<SalesReportInsolventUsers> sriuList = new ArrayList<>();
        try {
            sriuList = entityManager.createNamedQuery("SalesReportInsolventUsers.findAllInsolvent", SalesReportInsolventUsers.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return sriuList;
    }
}
