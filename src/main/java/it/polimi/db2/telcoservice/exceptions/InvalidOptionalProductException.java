package it.polimi.db2.telcoservice.exceptions;

public class InvalidOptionalProductException extends Exception{
    private static final long serialVersionUID = 1L;

    public InvalidOptionalProductException(int optProdId, int servPckgId) {
        super("Service package " + servPckgId + " cannot be associated with optional product " + optProdId + ".");
    }
}
