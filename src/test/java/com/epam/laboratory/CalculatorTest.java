package com.epam.laboratory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CalculatorTest {

    public String initialString = "2 + 3 * 45.3 * 90 + 20 - 8/20 - sqrt4";
    public String initialStringWithNegativeFirstNumber = "- 8/20 + 2 + 90 * 45.3 * 3 - sqrt4  + 20";
    public List<BigDecimal> numbers = Arrays.asList(
            new BigDecimal("2"), new BigDecimal("3"),
            new BigDecimal("45.3"), new BigDecimal("90"),
            new BigDecimal("20"), new BigDecimal("8"),
            new BigDecimal("20"), new BigDecimal("4")
    );

    public List<String> arithmeticOperations = Arrays.asList(
            "+", "*", "*", "+", "-", "/", "-sqrt"
    );
    private final BigDecimal answer = new BigDecimal("12250.6");
    private final Calculator calculator = new Calculator();

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
    public void testCalculateMathExpression() {
        Assert.assertEquals(calculator.calculateMathExpression(initialString).doubleValue(), answer.doubleValue(), 0.0);
    }

    @AfterAll
    @Test
    public void testCalculateMathExpressionWithNegativeFirstNumber() {
        Assert.assertEquals(calculator.calculateMathExpression(initialStringWithNegativeFirstNumber).doubleValue(), answer.doubleValue(), 0.0);
    }

}
