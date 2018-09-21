package com.smart.scw.restapi.exception.CustomException;

public class GetDataFailExcepiton extends Exception {

    public GetDataFailExcepiton() {
    }

    public GetDataFailExcepiton(String message) {
        super(message);
    }

    public GetDataFailExcepiton(String message, Throwable cause) {
        super(message, cause);
    }

    public GetDataFailExcepiton(Throwable cause) {
        super(cause);
    }

    public GetDataFailExcepiton(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
