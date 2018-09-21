package com.smart.scw.restapi.exception.CustomException;

public class GetCertsFailException extends  Exception {

    public GetCertsFailException() {
    }

    public GetCertsFailException(String message) {
        super(message);
    }

    public GetCertsFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetCertsFailException(Throwable cause) {
        super(cause);
    }

    public GetCertsFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
