package com.jc.local.config;

import com.jc.local.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * RestControllerAdvice 定义全局的异常处理注解
 */
@Slf4j
@RestControllerAdvice
public class ExceptionConfig {
    /**
     * ExceptionHandler 定义在异常处理方法上
     * getRequestURL  在发生异常的时候把url打印在控制台
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(Exception.class)
    public <T> Response<T> exceptionHandler(Exception e,HttpServletRequest httpServletRequest) {
        log.error("发生了异常",httpServletRequest.getRequestURL() );
        return Response.failure(e.getMessage());
    }
}
