package com.springcore.topic10_spel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/*
 * Topic    : SpEL - Spring Expression Language
 * Key idea : SpEL = a small formula language inside Spring, like formulas in an Excel cell.
 *            - #{...} works out a value when the bean is created.
 *            - It can do maths, call methods, read other beans' values,
 *              check conditions and filter lists.
 *            - You will see it again in @Value, @Cacheable keys and Spring Security rules.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic10_spel.SpelDemo
 * Try this : Add a field with #{pricing.prices.![#this * 2]}
 *            (this is a "projection": it changes every element of the list - here, doubles it).
 */
public class SpelDemo {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(SpelDemo.class.getPackageName())) {
            System.out.println("Values injected with @Value(\"#{...}\"):");
            System.out.println(context.getBean(SpelExamples.class));
        }

        // SpEL also works on its own, without any bean or container
        ExpressionParser parser = new SpelExpressionParser();
        System.out.println("\nStandalone parser: 'Spring'.length() * 2 = "
                + parser.parseExpression("'Spring'.length() * 2").getValue());
    }
}
