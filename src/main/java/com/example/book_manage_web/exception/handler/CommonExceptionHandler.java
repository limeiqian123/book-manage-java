package com.example.book_manage_web.exception.handler;

import com.example.book_manage_web.dto.Response;
import com.example.book_manage_web.exception.BizException;
import com.example.book_manage_web.exception.ExceptionEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CommonExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Response bizExceptionHandler(HttpServletRequest req, BizException e){
        logger.error("BizException: {}",e.getErrorMessage());
        return Response.error(e.getErrorCode(),e.getErrorMessage());
    }

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("unknown exception :",e);
        return Response.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }
}
