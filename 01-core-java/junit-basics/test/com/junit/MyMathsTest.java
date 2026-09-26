package com.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyMathsTest {

    @Test
    void test() {
        int [] numbers = {1,2,3};
        MyMaths math = new MyMaths();

        int result = math.calsum(numbers);

        System.out.println(result);
        int expectedResult=6;
        assertEquals(expectedResult, result);
        // Proper assertion instead of println
        //assertEquals(6, result, "Sum of 1,2,3 should be 6");
    }
}
