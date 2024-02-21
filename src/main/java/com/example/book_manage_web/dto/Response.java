package com.example.book_manage_web.dto;


import com.example.book_manage_web.exception.ExceptionEnum;
import com.example.book_manage_web.exception.IBaseError;

import java.io.Serializable;
import java.time.format.ResolverStyle;

public class Response implements Serializable {
    private int code;
    private String message;
    private Object data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Response() {
    }

    public static Response success() {
        return success(null);
    }

    public static Response success(Object data) {
        Response rb = new Response();
        rb.setCode(ExceptionEnum.SUCCESS.getResultCode());
        rb.setMessage(ExceptionEnum.SUCCESS.getResultMessage());
        rb.setData(data);
        return rb;
    }

    public static Response error(IBaseError errorInfo) {
        Response rb = new Response();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMessage());
        rb.setData(null);
        return rb;
    }

    public static Response error(int code, String message) {
        Response rb = new Response();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

    public static Response error( String message) {
        Response rb = new Response();
        rb.setCode(-1);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
