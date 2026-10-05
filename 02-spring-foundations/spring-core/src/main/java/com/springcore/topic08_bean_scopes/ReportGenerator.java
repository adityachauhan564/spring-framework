package com.springcore.topic08_bean_scopes;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/*
 * @Lazy singleton: NOT created at startup. It is created only when someone asks for it the first time.
 * Useful for heavy beans that are rarely used - like a generator that is switched on only when the power goes.
 */
@Component
@Lazy
public class ReportGenerator {

    public ReportGenerator() {
        System.out.println("  ReportGenerator created (lazy - only now, on first use)");
    }
}
