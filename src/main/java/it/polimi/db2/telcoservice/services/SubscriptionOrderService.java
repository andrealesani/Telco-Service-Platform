package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.*;
import it.polimi.db2.telcoservice.exceptions.InvalidOptionalProductException;
import it.polimi.db2.telcoservice.exceptions.InvalidValidityPeriodException;
import it.polimi.db2.telcoservice.exceptions.PastStartDateException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class SubscriptionOrderService {
	@PersistenceContext
	private EntityManager entityManager;
	@EJB(name = "it.polimi.db2.telcoservice.services/ServicePackageService")
	private ServicePackageService spService;
	@EJB(name = "it.polimi.db2.telcoservice.services/ValidityPeriodService")
	private ValidityPeriodService vpService;
	@EJB(name = "it.polimi.db2.telcoservice.services/OptionalProductService")
	private OptionalProductService opService;
	@EJB(name = "it.polimi.db2.telcoservice.services/UserService")
	private UserService uService;

	public SubscriptionOrder findSubscriptionOrderById(int id) {
		return entityManager.find(SubscriptionOrder.class, id);
	}

	public void makePayment(int id, boolean valid, int userId) {

		User user = uService.findUserById(userId);

		entityManager.createNamedQuery("SubscriptionOrder.makePayment", SubscriptionOrder.class)
				.setParameter(1, id).setParameter(2, valid).setParameter(3, user)
				.executeUpdate();
	}

	public SubscriptionOrder createOrder(int servicePackageID, int validityPeriodID, List<Integer> optionalProductIDs, Timestamp creationTs, Timestamp startDateTs) throws InvalidOptionalProductException, InvalidValidityPeriodException, PastStartDateException {

		if (startDateTs.before(new Timestamp(System.currentTimeMillis()))) {
			throw new PastStartDateException();
		}

		ServicePackage servicePackage = spService.findServicePackageById(servicePackageID);

		if (servicePackage.getValidityPeriods().stream().noneMatch(o -> o.getId() == validityPeriodID)) {
			throw new InvalidValidityPeriodException(validityPeriodID, servicePackageID);
		}
		ValidityPeriod validityPeriod = vpService.findValidityPeriodById(validityPeriodID);

		Set<OptionalProduct> optionalProducts = new HashSet<>();
		for (Integer optionalProductID : optionalProductIDs) {
			if (servicePackage.getOptionalProducts().stream().noneMatch(o -> o.getId() == optionalProductID)) {
				throw new InvalidOptionalProductException(optionalProductID, servicePackageID);
			}
			optionalProducts.add(opService.findOptionalProductById(optionalProductID));
		}

		SubscriptionOrder order = new SubscriptionOrder(servicePackage, validityPeriod, optionalProducts, creationTs, startDateTs);
		entityManager.persist(order);
		return order;
	}
}
