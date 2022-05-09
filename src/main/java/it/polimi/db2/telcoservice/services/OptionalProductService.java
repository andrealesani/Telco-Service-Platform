package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.OptionalProduct;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OptionalProductService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<OptionalProduct> findAllOptionalProducts() {
        List<OptionalProduct> opList = new ArrayList<>();
        try {
            opList = entityManager.createNamedQuery("OptionalProduct.findAllOptionalProducts", OptionalProduct.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return opList;
    }

    public OptionalProduct findOptionalProductById(int id) {
        return entityManager.find(OptionalProduct.class, id);
    }

    public int findNumOptionalProducts() {
        int numProducts = 0;
        try {
            numProducts = ((Number) entityManager.createNamedQuery("OptionalProduct.findNumOptionalProducts", Integer.class)
                    .getSingleResult()).intValue();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return numProducts;
    }

    public OptionalProduct createOptionalProduct(String name, BigDecimal monthlyFee) {
        OptionalProduct optionalProduct = new OptionalProduct(name, monthlyFee);
        entityManager.persist(optionalProduct);
        return optionalProduct;
    }
}
