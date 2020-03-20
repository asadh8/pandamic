package com.entrue.pandamic.exception.elasticsearch;

public class InvalidInputException extends Exception {

    private static final long serialVersionUID = 6050917652650084612L;

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidInputException(String errorMessage, Throwable t) {
        super(errorMessage, t);
    }
}
