package com.entrue.pandamic.exception;

/**
 *
 * @author asadhsheriff
 */
public class StockCreationException extends Exception {

    private static final long serialVersionUID = -2063299117279493496L;

    public StockCreationException() {
        super();
    }

    public StockCreationException(String errorMessage) {
        super(errorMessage);
    }

    public StockCreationException(String errorMessage, Throwable t) {
        super(errorMessage, t);
    }
}
