package com.springcore;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.springcore.topic13_aop.AopConfig;
import com.springcore.topic13_aop.OrderService;

/* Proves the aspects really run, by capturing what they print. */
@SpringJUnitConfig(AopConfig.class)
class AopTest {

    @Autowired
    OrderService orderService;

    private final ByteArrayOutputStream captured = new ByteArrayOutputStream();
    private PrintStream original;

    @BeforeEach
    void captureOutput() {
        original = System.out;
        System.setOut(new PrintStream(captured, true));
    }

    @AfterEach
    void restoreOutput() {
        System.setOut(original);
    }

    @Test
    void beanIsAProxy() {
        assertTrue(AopUtils.isAopProxy(orderService));
    }

    @Test
    void loggingAndTimingAdviceRunAroundTheMethod() {
        orderService.placeOrder("laptop");
        String out = captured.toString();
        assertTrue(out.contains("[log] -> placeOrder"));
        assertTrue(out.contains("[timing] placeOrder"));
    }

    @Test
    void exceptionIsLoggedAndStillReachesTheCaller() {
        assertThrows(IllegalArgumentException.class, () -> orderService.cancelOrder(-1));
        assertTrue(captured.toString().contains("[log] !! cancelOrder"));
    }

    @Test
    void selfInvocationBypassesTheProxy() {
        orderService.placeTwoOrders();
        assertFalse(captured.toString().contains("[timing]"));
    }
}
