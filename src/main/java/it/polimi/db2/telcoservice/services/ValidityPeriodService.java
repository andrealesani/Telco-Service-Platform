package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.ValidityPeriod;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ValidityPeriodService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<ValidityPeriod> findAllValidityPeriods() {
        List<ValidityPeriod> vpList = new ArrayList<>();
        try {
            vpList = entityManager.createNamedQuery("ValidityPeriod.findAllValidityPeriods", ValidityPeriod.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return vpList;
    }

    public ValidityPeriod findValidityPeriodById(int id) {
        return entityManager.find(ValidityPeriod.class, id);
    }

    public int findNumValidityPeriods() {
        int numPeriods = 0;
        try {
            numPeriods = ((Number) entityManager.createNamedQuery("ValidityPeriod.findNumValidityPeriods", Integer.class)
                    .getSingleResult()).intValue();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return numPeriods;
    }
}
