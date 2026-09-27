package com.springcore.topic08_bean_scopes;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/* @Lazy singleton: not created at startup, only when first requested (useful for heavy, rarely used beans). */
@Component
@Lazy
public class ReportGenerator {

    public ReportGenerator() {
        System.out.println("  ReportGenerator created (lazy - only now, on first use)");
    }
}
