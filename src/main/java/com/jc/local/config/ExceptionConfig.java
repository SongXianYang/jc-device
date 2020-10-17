package com.jc.local.config;

import com.jc.local.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(Exception.class)
    public <T> Response<T> exceptionHandler(Exception e, HttpServletRequest httpServletRequest) {
        log.error("Invoked {} exception.", httpServletRequest.getRequestURL());
        return Response.failure(e.getMessage());
    }
}
