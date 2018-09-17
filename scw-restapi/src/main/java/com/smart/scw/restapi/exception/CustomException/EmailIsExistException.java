package com.smart.scw.restapi.exception.CustomException;

public class EmailIsExistException extends Exception {

    public EmailIsExistException() {
    }

    public EmailIsExistException(String message) {
        super(message);
    }

    public EmailIsExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailIsExistException(Throwable cause) {
        super(cause);
    }

    public EmailIsExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
