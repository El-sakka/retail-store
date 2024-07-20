package org.retail.store.exception;

public class BillNotFoundException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Bill not found";

    public BillNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public BillNotFoundException(String message) {
        super(message);
    }

    public BillNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}