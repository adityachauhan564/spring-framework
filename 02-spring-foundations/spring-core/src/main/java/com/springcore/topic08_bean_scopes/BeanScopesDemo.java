package com.springcore.topic08_bean_scopes;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Topic    : Bean scopes - singleton, prototype, and @Lazy
 * Key idea : singleton (default) = one shared instance per container, created at startup.
 *            prototype           = a new instance on every getBean / injection.
 *            Web apps add request and session scopes (see spring-mvc).
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic08_bean_scopes.BeanScopesDemo
 * Try this : remove @Scope("prototype") from ShoppingCart and predict every line of output.
 */
public class BeanScopesDemo {

    public static void main(String[] args) {
        System.out.println("Starting the container:");
        try (var context = new AnnotationConfigApplicationContext(BeanScopesDemo.class.getPackageName())) {

            AppSettings a = context.getBean(AppSettings.class);
            AppSettings b = context.getBean(AppSettings.class);
            System.out.println("\nsingleton: same object? " + (a == b));

            ShoppingCart cart1 = context.getBean(ShoppingCart.class);
            ShoppingCart cart2 = context.getBean(ShoppingCart.class);
            System.out.println("prototype: same object? " + (cart1 == cart2) + " (cart ids " + cart1.getId() + ", " + cart2.getId() + ")");

            CheckoutService checkout = context.getBean(CheckoutService.class);
            System.out.println("\nprototype injected into a singleton - calls 1, 2: cart "
                    + checkout.cartFromConstructor() + ", cart " + checkout.cartFromConstructor() + "   (shared - the pitfall)");
            System.out.println("with ObjectProvider           - calls 1, 2: cart "
                    + checkout.freshCart() + ", cart " + checkout.freshCart() + "   (fresh each time)");

            System.out.println("\nAsking for the @Lazy bean:");
            context.getBean(ReportGenerator.class);
        }
    }
}
