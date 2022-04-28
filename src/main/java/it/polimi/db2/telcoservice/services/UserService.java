package it.polimi.db2.telcoservice.services;

import it.polimi.db2.telcoservice.entities.Service;
import it.polimi.db2.telcoservice.entities.User;
import it.polimi.db2.telcoservice.exceptions.CredentialsException;
import it.polimi.db2.telcoservice.exceptions.UserAlreadyExistsException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import java.util.List;

@Stateless
public class UserService {
	@PersistenceContext
	EntityManager entityManager;

	public User findUserById(int id) {
		return entityManager.find(User.class, id);
	}

	public User checkCredentials(String username, String password) throws CredentialsException, NonUniqueResultException {
		List<User> uList;
		try {
			uList = entityManager.createNamedQuery("User.checkCredentials", User.class).setParameter(1, username).setParameter(2, password)
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

	public void registerNewUser(String username, String password, String email) throws CredentialsException, UserAlreadyExistsException {
		List<User> uList;
		try {
			uList = entityManager.createNamedQuery("User.existsUsername", User.class).setParameter(1, username)
					.getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify credentials");
		}
		if (uList.isEmpty()) {
			User user = new User(username, password, email, false, 0);
			entityManager.persist(user);
		}
		else
			throw new UserAlreadyExistsException("The specified username has already been used");
	}
}
