package com.mobiquity.packer.exception;

/**
 * Exception handler
 */
public class APIException extends Exception {

    private String message;

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
