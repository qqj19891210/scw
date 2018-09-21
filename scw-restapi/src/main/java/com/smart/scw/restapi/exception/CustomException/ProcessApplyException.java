package com.smart.scw.restapi.exception.CustomException;

public class ProcessApplyException extends Exception {

    public ProcessApplyException() {
    }

    public ProcessApplyException(String message) {
        super(message);
    }

    public ProcessApplyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessApplyException(Throwable cause) {
        super(cause);
    }

    public ProcessApplyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
