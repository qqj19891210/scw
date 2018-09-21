package com.smart.scw.restapi.exception.CustomException;

public class MemberCertFailException extends Exception {

    public MemberCertFailException() {
    }

    public MemberCertFailException(String message) {
        super(message);
    }

    public MemberCertFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberCertFailException(Throwable cause) {
        super(cause);
    }

    public MemberCertFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
