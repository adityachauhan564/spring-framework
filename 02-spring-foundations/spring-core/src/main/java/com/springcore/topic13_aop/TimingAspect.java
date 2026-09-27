package com.springcore.topic13_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
 * @Around wraps the call: code before, proceed() runs the real method, code after.
 * The pointcut selects methods by ANNOTATION instead of by name.
 */
@Aspect
@Component
public class TimingAspect {

    @Around("@annotation(com.springcore.topic13_aop.Timed)")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();          // forgetting proceed() means the method never runs
        } finally {
            long micros = (System.nanoTime() - start) / 1_000;
            System.out.println("  [timing] " + joinPoint.getSignature().getName() + " took " + micros + " us");
        }
    }
}
