package com.springcore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.springcore.topic08_bean_scopes.AppSettings;
import com.springcore.topic08_bean_scopes.CheckoutService;
import com.springcore.topic08_bean_scopes.ShoppingCart;

/* @SpringJUnitConfig starts a container for the test and injects beans into it. */
@SpringJUnitConfig(BeanScopesTest.Config.class)
class BeanScopesTest {

    @Configuration
    @ComponentScan(basePackageClasses = ShoppingCart.class)
    static class Config {
    }

    @Autowired
    ApplicationContext context;

    @Test
    void singletonIsShared() {
        assertSame(context.getBean(AppSettings.class), context.getBean(AppSettings.class));
    }

    @Test
    void prototypeIsNewEachTime() {
        assertNotSame(context.getBean(ShoppingCart.class), context.getBean(ShoppingCart.class));
    }

    @Test
    void prototypeInsideSingletonIsFetchedOnlyOnce() {
        CheckoutService checkout = context.getBean(CheckoutService.class);
        assertEquals(checkout.cartFromConstructor(), checkout.cartFromConstructor());
        assertNotEquals(checkout.freshCart(), checkout.freshCart());
    }
}
