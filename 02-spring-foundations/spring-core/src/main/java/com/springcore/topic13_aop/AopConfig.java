package com.springcore.topic13_aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/* @EnableAspectJAutoProxy: wrap every bean that an @Aspect matches in a proxy. */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AopConfig {
}
