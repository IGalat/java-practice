package com.jr.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Very simple logging before and after each method
 */
@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("execution(* com.jr..*(..))")
    public void generalBeforeLoggingAspect(JoinPoint joinPoint) {
        logger.trace("Before method {} with args {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.jr..*(..))", returning = "returnObj")
    public void generalReturnLoggingAspect(JoinPoint joinPoint, Object returnObj) {
        logger.trace("After method {} returns {}", joinPoint.getSignature(), returnObj);
    }

    @AfterThrowing(value = "execution(* com.jr..*(..))", throwing = "ex")
    public void generalExceptionLoggingAspect(JoinPoint joinPoint, Exception ex) {
        logger.trace("Exception in method " + joinPoint.getSignature(), ex);
    }
}
