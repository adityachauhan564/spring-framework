package com.springcore.topic13_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
 * @Around wraps the whole call: some code before, then proceed() runs the real method, then some code after.
 * Like a stopwatch: start it, let the runner run, stop it.
 * This pointcut picks methods by their ANNOTATION (@Timed), not by their name.
 */
@Aspect
@Component
public class TimingAspect {

    @Around("@annotation(com.springcore.topic13_aop.Timed)")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();          // if you forget proceed(), the real method never runs at all
        } finally {
            long micros = (System.nanoTime() - start) / 1_000;
            System.out.println("  [timing] " + joinPoint.getSignature().getName() + " took " + micros + " us");
        }
    }
}
