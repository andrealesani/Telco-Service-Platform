package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.OptionalProduct;
import it.polimi.db2.telcoservice.entities.Service;
import it.polimi.db2.telcoservice.entities.ServicePackage;
import it.polimi.db2.telcoservice.entities.ValidityPeriod;
import it.polimi.db2.telcoservice.entities.materialized.SalesReportProductSales;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
public class ServiceService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Service> findAllServices() {
        List<Service> sList = new ArrayList<>();
        try {
            sList = entityManager.createNamedQuery("Service.findAllServices", Service.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return sList;
    }

    public Service findServiceById(int id) {
        return entityManager.find(Service.class, id);
    }

    public int findNumServices() {
        int numServices = 0;
        try {
            numServices = ((Number) entityManager.createNamedQuery("Service.findNumServices", Integer.class)
                    .getSingleResult()).intValue();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return numServices;
    }
}
