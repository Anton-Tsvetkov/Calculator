package com.epam.laboratory;

import com.epam.laboratory.exceptions.DivisionByZeroException;
import com.epam.laboratory.exceptions.IncorrectOperationEntryException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class CalculatorTest {

    private static final String MATH_EXPRESSION = "2 + 3 * 45.3 * 90 + 20 - 8/20 - sqrt4";
    private static final String MATH_EXPRESSION_WITH_NEGATIVE_FIRST_NUMBER = "- 8/20 + 2 + 90 * 45.3 * 3 - sqrt4  + 20";
    private static final String MATH_EXPRESSION_WITH_INCORRECT_OPERATION_ENTRY = "2 + 3 ** 45.3 * 90 + 20 -- 8/20 - st4";
    private static final List<BigDecimal> NUMBERS_FROM_MATH_EXPRESSION = Arrays.asList(
            new BigDecimal("2"), new BigDecimal("3"),
            new BigDecimal("45.3"), new BigDecimal("90"),
            new BigDecimal("20"), new BigDecimal("8"),
            new BigDecimal("20"), new BigDecimal("4")
    );

    private static final List<String> ARITHMETIC_OPERATIONS_FROM_MATH_EXPRESSION = Arrays.asList(
            "+", "*", "*", "+", "-", "/", "-sqrt"
    );

    private static final Calculator CALCULATOR = new Calculator();
    private static final Parser PARSER = new Parser();

    private static final BigDecimal SOLUTION_MATH_EXPRESSION = new BigDecimal("12250.6");
    private static final BigDecimal SIMPLE_TEST_NUMERIC_A = new BigDecimal("5396");
    private static final BigDecimal SIMPLE_TEST_NUMERIC_B = new BigDecimal("732");
    private static final BigDecimal NULL = new BigDecimal("0");
    private static final BigDecimal ONE = new BigDecimal("1");

    @Test
    public void testExtractionNumbersFromMathExpression() {
        Assert.assertEquals(
                PARSER.extractionNumbers(MATH_EXPRESSION),
                NUMBERS_FROM_MATH_EXPRESSION);
    }

    @Test
    public void testExtractionArithmeticOperationsFromMathExpression() {
        Assert.assertEquals(
                PARSER.extractionEverythingExceptNumbers(MATH_EXPRESSION),
                ARITHMETIC_OPERATIONS_FROM_MATH_EXPRESSION);
    }

    @Test
    public void testCalculateMathExpression() {
        Assert.assertEquals(
                CALCULATOR.calculateMathExpression(MATH_EXPRESSION).doubleValue(),
                SOLUTION_MATH_EXPRESSION.doubleValue(), 0.0);
    }

    @Test
    public void testCalculateMathExpressionWithNegativeFirstNumber() {
        Assert.assertEquals(
                CALCULATOR.calculateMathExpression(MATH_EXPRESSION_WITH_NEGATIVE_FIRST_NUMBER).doubleValue(),
                SOLUTION_MATH_EXPRESSION.doubleValue(), 0.0);
    }


    @Test
    public void testCommunicativeAdditionLaw() {
        Assert.assertEquals(
                CALCULATOR.addition(SIMPLE_TEST_NUMERIC_A, SIMPLE_TEST_NUMERIC_B),
                CALCULATOR.addition(SIMPLE_TEST_NUMERIC_B, SIMPLE_TEST_NUMERIC_A));
    }

    @Test
    public void testAdditionNullDoesNotChangeNumber() {
        Assert.assertEquals(
                CALCULATOR.addition(SIMPLE_TEST_NUMERIC_A, NULL),
                SIMPLE_TEST_NUMERIC_A);
    }


    @Test
    public void testSubtractionNullDoesNotChangeNumber() {
        Assert.assertEquals(
                CALCULATOR.subtraction(SIMPLE_TEST_NUMERIC_A, NULL),
                SIMPLE_TEST_NUMERIC_A);
    }

    @Test
    public void testDifferenceBetweenNumberAndItselfIsNull() {
        Assert.assertEquals(
                CALCULATOR.subtraction(SIMPLE_TEST_NUMERIC_A, SIMPLE_TEST_NUMERIC_A),
                NULL);
    }


    @Test
    public void testCommunicativeMultiplicationLaw() {
        Assert.assertEquals(
                CALCULATOR.multiply(SIMPLE_TEST_NUMERIC_A, SIMPLE_TEST_NUMERIC_B),
                CALCULATOR.multiply(SIMPLE_TEST_NUMERIC_B, SIMPLE_TEST_NUMERIC_A));
    }

    @Test
    public void testMultiplicationNumberByNullIsNull() {
        Assert.assertEquals(
                CALCULATOR.multiply(SIMPLE_TEST_NUMERIC_A, NULL),
                NULL);
    }

    @Test
    public void testMultiplicationNumberByOneIsNumber() {
        Assert.assertEquals(
                CALCULATOR.multiply(SIMPLE_TEST_NUMERIC_A, ONE),
                SIMPLE_TEST_NUMERIC_A);
    }


    @Test
    public void testDivisionNumberByOneIsNumber() throws DivisionByZeroException {
        Assert.assertEquals(
                CALCULATOR.division(SIMPLE_TEST_NUMERIC_A, ONE),
                SIMPLE_TEST_NUMERIC_A);
    }

    @Test
    public void testDivisionNullByNumberIsNull() throws DivisionByZeroException {
        Assert.assertEquals(
                CALCULATOR.division(NULL, SIMPLE_TEST_NUMERIC_B), NULL);
    }

    @Test
    public void testDivisionNumberByNumberIsOne() throws DivisionByZeroException {
        Assert.assertEquals(
                CALCULATOR.division(SIMPLE_TEST_NUMERIC_B, SIMPLE_TEST_NUMERIC_B),
                ONE);
    }

    @Test
    public void testDivisionNumberByZeroException() {
        try {
            CALCULATOR.division(SIMPLE_TEST_NUMERIC_A, NULL);
        } catch (DivisionByZeroException exception) {
            Assert.assertThat(exception.getMessage(), is("Division by zero"));
        }
    }

    @Test
    public void testIncorrectOperationEntryException() {
        try {
            PARSER.extractionEverythingExceptNumbers(MATH_EXPRESSION_WITH_INCORRECT_OPERATION_ENTRY);
        } catch (IncorrectOperationEntryException exception) {
            Assert.assertThat(exception.getMessage(), is("Incorrect arithmetic operation"));
        }
    }

}
