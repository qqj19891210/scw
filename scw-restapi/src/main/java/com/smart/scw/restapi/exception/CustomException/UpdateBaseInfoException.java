package com.smart.scw.restapi.exception.CustomException;

public class UpdateBaseInfoException extends Exception {

    public UpdateBaseInfoException() {
    }

    public UpdateBaseInfoException(String message) {
        super(message);
    }

    public UpdateBaseInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateBaseInfoException(Throwable cause) {
        super(cause);
    }

    public UpdateBaseInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
