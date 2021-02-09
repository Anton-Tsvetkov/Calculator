package com.epam.laboratory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class AppTest {

    private static final String initialString = "2 + 3 * 45.3 * 90 + 20 - 8/20 - sqrt4";
    private static final double answer = 12_250.6;

    private static final Calculator calculator = new Calculator();

    @BeforeAll
    @Test
    public static void testExtractionNumbersFromMathExpression(){

    }

    @BeforeAll
    @Test
    public static void testExtractionArithmeticOperationsFromMathExpression(){

    }

    @AfterAll
    @Test
    public static void testCalculateMathExpression() {
        Assert.assertEquals(calculator.calculateMathExpression(initialString), answer, 0.0);
    }

}
