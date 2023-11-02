package com.sushmanth.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sushmanth.exception.EmployeeException;

@Aspect
@Component
public class ResponseLoggingAspect {
    private static final Logger log = LogManager.getLogger(ResponseLoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.sushmanth.service..*(..))", throwing = "e")
    public void logException(EmployeeException e) {
        log.error("Exception occurred: {}", e.getMessage(), e);
    }
    
    @AfterReturning(pointcut = "execution(* com.sushmanth.controller.*.*(..))", returning = "result")
    public void logSuccessfulResponse(Object result) {
        log.info("Successful response: {}", result);
    }
}
