package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.Auditing;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AuditingService {
    public List<Auditing> findAllAuditings() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Auditing> aList = new ArrayList<>();
        try {
            aList = entityManager.createNamedQuery("Auditing.findAllAuditings", Auditing.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return aList;
    }
}
