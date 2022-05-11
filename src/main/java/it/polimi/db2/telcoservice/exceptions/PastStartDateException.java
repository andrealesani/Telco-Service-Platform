package it.polimi.db2.telcoservice.exceptions;

public class PastStartDateException extends Exception{
    private static final long serialVersionUID = 1L;

    public PastStartDateException() {
        super("The selected start date is before the current date.");
    }
}
