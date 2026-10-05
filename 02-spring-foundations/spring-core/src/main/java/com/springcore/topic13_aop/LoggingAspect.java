package com.springcore.topic13_aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
 * An aspect = code needed by MANY methods, kept in ONE place.
 * Such code is called a "cross-cutting concern": logging, security, transactions.
 * Like CCTV in a mall - it covers every shop, but no shop has to install its own.
 *   pointcut - WHICH methods  (here: every public method of OrderService)
 *   advice   - WHAT to run and WHEN (@Before, @AfterReturning, @AfterThrowing, @Around)
 */
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(public * com.springcore.topic13_aop.OrderService.*(..))")
    void orderServiceMethods() {
    }

    @Before("orderServiceMethods()")
    public void logCall(JoinPoint joinPoint) {
        System.out.println("  [log] -> " + joinPoint.getSignature().getName() + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "orderServiceMethods()", returning = "result")
    public void logResult(JoinPoint joinPoint, Object result) {
        System.out.println("  [log] <- " + joinPoint.getSignature().getName() + " returned " + result);
    }

    @AfterThrowing(pointcut = "orderServiceMethods()", throwing = "error")
    public void logError(JoinPoint joinPoint, Exception error) {
        System.out.println("  [log] !! " + joinPoint.getSignature().getName() + " threw " + error.getMessage());
    }
}
