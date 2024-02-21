package com.example.book_manage_web.exception;

import com.oracle.jrockit.jfr.InvalidValueException;

public class BizException extends RuntimeException {

    protected Integer errorCode;
    protected String errorMessage;

    public BizException(IBaseError baseError) {
        super(baseError.getResultCode().toString());
        errorCode = baseError.getResultCode();
        errorMessage = baseError.getResultMessage();
    }

    public BizException(IBaseError baseError, Throwable throwable) {
        super(baseError.getResultCode().toString(), throwable);
        errorCode = baseError.getResultCode();
        errorMessage = baseError.getResultMessage();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        errorMessage = errorMsg;
    }

    public BizException(Integer code, String msg) {
        super(code.toString());
        errorCode = code;
        errorMessage = msg;
    }

    public BizException(Integer code, String msg, Throwable throwable) {
        super(code.toString(), throwable);
        errorCode = code;
        errorMessage = msg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
