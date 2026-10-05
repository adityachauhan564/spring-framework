package com.springcore.topic13_aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/* @EnableAspectJAutoProxy: every bean that an @Aspect matches gets wrapped in a proxy (a stand-in object in front of it). */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AopConfig {
}
