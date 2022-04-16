package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.materialized.SalesReportInsolventUsers;
import it.polimi.db2.telcoservice.entities.materialized.SalesReportValidityPackages;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SalesReportInsolventUsersService {
    public List<SalesReportInsolventUsers> findAllInsolvent() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
