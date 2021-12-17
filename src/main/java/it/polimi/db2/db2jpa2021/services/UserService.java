package it.polimi.db2.db2jpa2021.services;

import it.polimi.db2.db2jpa2021.entities.User;
import it.polimi.db2.db2jpa2021.exceptions.CredentialsException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class UserService {
	// @PersistenceContext(unitName = "db2_jpa")
	@PersistenceContext
	private EntityManager em;

	public User checkCredentials(String username, String password) throws CredentialsException, NonUniqueResultException {
		List<User> uList;
		try {
			uList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, username).setParameter(2, password)
					.getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify credentials");
		}
		if (uList.isEmpty())
			return null;
		else if (uList.size() == 1)
			return uList.get(0);
		throw new NonUniqueResultException("More than one user registered with same credentials");
	}
}
