package com.epam.laboratory;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    private static final String initialString = "2 + 3 * 45.3 * 90 + 20 - 8/20 - sqrt4";
    private static final String prioritizedString = "3 * 45.3 * 90 - 8/20 - sqrt4 + 2 + 20";
    private static final double answer = 12_250.6;

    Calculator calculator = new Calculator();

    @Test
    public void testCalculateMathExpression() {
        Assert.assertEquals(calculator.calculateMathExpression(initialString), answer, 0.0);
    }

}
