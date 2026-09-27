package com.springcore.topic10_spel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/*
 * Topic    : SpEL - Spring Expression Language
 * Key idea : #{...} computes a value at bean-creation time: maths, method calls, other
 *            beans' properties, conditions, collection filtering. You'll meet it again in
 *            @Value, @Cacheable keys and Spring Security rules.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic10_spel.SpelDemo
 * Try this : add a field with #{pricing.prices.![#this * 2]} (projection: transform every element).
 */
public class SpelDemo {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(SpelDemo.class.getPackageName())) {
            System.out.println("Values injected with @Value(\"#{...}\"):");
            System.out.println(context.getBean(SpelExamples.class));
        }

        // SpEL also works on its own, without any bean
        ExpressionParser parser = new SpelExpressionParser();
        System.out.println("\nStandalone parser: 'Spring'.length() * 2 = "
                + parser.parseExpression("'Spring'.length() * 2").getValue());
    }
}
