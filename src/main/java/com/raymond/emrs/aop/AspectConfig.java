package com.raymond.emrs.aop;

import com.raymond.emrs.exceptions.ResourceNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AspectConfig {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * logging
     * Exception Handling
     */

    @Before(value = "execution(* com.raymond.emrs.controller.*.delete(..))")
    public void logBeforeDeleteAndUpdate(JoinPoint joinPoint) {
        log.info("Executing {}", joinPoint);
    }

    @Around(value = "execution(* com.raymond.emrs.service.*.*(..))")
    public Object exceptionTracker(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object obj = joinPoint.proceed();
            return obj;
        } catch (ResourceNotFoundException resourceNotFoundException) {
            log.info("ResourceNotFoundException StatusCode {}", resourceNotFoundException.getHttpStatus().value());
            log.info("ResourceNotFoundException Message {}", resourceNotFoundException.getMessage());
        }
        return null;
    }
}
