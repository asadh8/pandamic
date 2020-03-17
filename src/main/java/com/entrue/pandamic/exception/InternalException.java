package com.entrue.pandamic.exception;

public class InternalException extends Exception {

    private static final long serialVersionUID = -5505546240689098602L;

    public InternalException() {
        super();
    }

    public InternalException(String errorMessage) {
        super(errorMessage);
    }

    public InternalException(String errorMessage, Throwable t) {
        super(errorMessage, t);
    }
}
