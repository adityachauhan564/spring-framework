package com.springcore.topic08_bean_scopes;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/*
 * The classic trap: a SINGLETON that needs a PROTOTYPE.
 * The singleton is built only once, so the cart given to its constructor is also
 * fetched only once. Result: every customer would share the SAME cart #N.
 * Like a supermarket with only one trolley for all customers - wrong!
 * Fix: inject an ObjectProvider (a helper that can fetch a bean on demand),
 * and ask it for a fresh cart each time.
 */
@Component
public class CheckoutService {

    private final ShoppingCart injectedOnce;
    private final ObjectProvider<ShoppingCart> cartProvider;

    public CheckoutService(ShoppingCart injectedOnce, ObjectProvider<ShoppingCart> cartProvider) {
        this.injectedOnce = injectedOnce;
        this.cartProvider = cartProvider;
    }

    public int cartFromConstructor() {
        return injectedOnce.getId();              // always the SAME cart - wrong when each customer needs their own
    }

    public int freshCart() {
        return cartProvider.getObject().getId();  // a NEW cart (prototype) on every call - correct
    }
}
