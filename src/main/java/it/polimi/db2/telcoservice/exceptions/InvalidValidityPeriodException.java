package it.polimi.db2.telcoservice.exceptions;

public class InvalidValidityPeriodException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidValidityPeriodException(int valPeriodId, int servPckgId) {
       super("Service package " + servPckgId + " cannot be associated with validity period " + valPeriodId + ".");
    }
}
