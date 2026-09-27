package com.springcore.topic13_aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Topic    : AOP - Aspect-Oriented Programming
 * Read     : Timed -> OrderService -> LoggingAspect -> TimingAspect -> AopConfig -> AopDemo
 * A marker annotation: TimingAspect times every method that carries @Timed.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed {
}
