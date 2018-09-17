package com.smart.scw.manager.exception.CustomException;

public class DeployFailException extends Exception {

    public DeployFailException() {
    }

    public DeployFailException(String message) {
        super(message);
    }

    public DeployFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeployFailException(Throwable cause) {
        super(cause);
    }

    public DeployFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
