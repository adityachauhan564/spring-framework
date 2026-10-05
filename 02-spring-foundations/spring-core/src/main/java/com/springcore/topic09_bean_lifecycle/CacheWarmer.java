package com.springcore.topic09_bean_lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
 * Way 2: implement Spring's InitializingBean / DisposableBean interfaces.
 * Works without any XML settings, but now the class depends on Spring
 * (it imports Spring's interfaces). That is why this is the least preferred way.
 */
public class CacheWarmer implements InitializingBean, DisposableBean {

    public CacheWarmer() {
        System.out.println("  [CacheWarmer]    1. constructor");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("  [CacheWarmer]    3. afterPropertiesSet(): loading the cache");
    }

    @Override
    public void destroy() {
        System.out.println("  [CacheWarmer]    destroy(): clearing the cache");
    }
}
