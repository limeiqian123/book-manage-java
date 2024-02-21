package com.example.book_manage_web.exception;

import org.omg.CORBA.INTERNAL;

public enum ExceptionEnum implements IBaseError {

    SUCCESS(0, "SUCCESS"),
    INTERNAL_SERVER_ERROR(500, "Internal error");

    private final Integer resultCode;
    private final String resultMessage;

    ExceptionEnum(Integer code, String msg) {
        resultCode = code;
        resultMessage = msg;
    }

    @Override
    public Integer getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMessage() {
        return resultMessage;
    }
}

