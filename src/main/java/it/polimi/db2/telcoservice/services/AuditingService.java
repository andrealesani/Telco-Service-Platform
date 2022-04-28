package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.Auditing;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AuditingService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Auditing> findAllAuditings() {
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
