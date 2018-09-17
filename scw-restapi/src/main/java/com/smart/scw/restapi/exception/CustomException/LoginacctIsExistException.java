package com.smart.scw.restapi.exception.CustomException;

public class LoginacctIsExistException extends  Exception {

    public LoginacctIsExistException() {
    }

    public LoginacctIsExistException(String message) {
        super(message);
    }

    public LoginacctIsExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginacctIsExistException(Throwable cause) {
        super(cause);
    }

    public LoginacctIsExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
