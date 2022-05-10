package it.polimi.db2.telcoservice.exceptions;

public class UserAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
