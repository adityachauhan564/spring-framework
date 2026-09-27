package com.springcore.topic08_bean_scopes;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/*
 * The classic pitfall: a SINGLETON that depends on a PROTOTYPE.
 * The singleton is built once, so the cart injected into its constructor is also
 * fetched only once - every customer would share cart #N.
 * Fix: inject an ObjectProvider and ask it for a fresh cart each time.
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
        return injectedOnce.getId();              // always the same cart - wrong for per-customer data
    }

    public int freshCart() {
        return cartProvider.getObject().getId();  // a new prototype on every call - correct
    }
}
