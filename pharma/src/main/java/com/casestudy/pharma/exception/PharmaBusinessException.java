package com.casestudy.pharma.exception;

public class PharmaBusinessException extends Exception{
    private int errorCode;

    public PharmaBusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}




