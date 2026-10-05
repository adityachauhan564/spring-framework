package com.springcore.topic13_aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Topic    : AOP - Aspect-Oriented Programming
 * Read     : Timed -> OrderService -> LoggingAspect -> TimingAspect -> AopConfig -> AopDemo
 * A marker annotation (a label with no code inside): TimingAspect measures the time
 * of every method that has @Timed on it.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed {
}
