package com.epam.laboratory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;
import java.util.List;

public class AppTest {

    private static final String initialString = "2 + 3 * 45.3 * 90 + 20 - 8/20 - sqrt4";
    private static final List<Double> numbers = Arrays.asList(
            2.0, 3.0, 45.3, 90.0, 20.0, 8.0, 20.0, 4.0
    );

    private static final List<String> arithmeticOperations = Arrays.asList(
            "+", "*", "*", "+", "-", "/", "-sqrt"
    );
    private static final double answer = 12_250.6;

    private static final Calculator calculator = new Calculator();

    @BeforeAll
    @Test
    public void testExtractionNumbersFromMathExpression() {
        Assert.assertEquals(calculator.extractionNumbersFromMathExpression(initialString), numbers);
    }

    @BeforeAll
    @Test
    public void testExtractionArithmeticOperationsFromMathExpression() {
        Assert.assertEquals(calculator.extractionArithmeticOperationsFromMathExpression(initialString), arithmeticOperations);
    }

    @AfterAll
    @Test
    public static void testCalculateMathExpression() {
        Assert.assertEquals(calculator.calculateMathExpression(initialString), answer, 0.0);
    }

}
