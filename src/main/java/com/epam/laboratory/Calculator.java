package com.epam.laboratory;

import com.epam.laboratory.exceptions.DivisionByZeroException;
import com.epam.laboratory.exceptions.IncorrectOperationEntryException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private String mathExpression;
    private List<BigDecimal> numbersFromMathExpression;
    private List<String> arithmeticOperationsFromMathExpression;
    private final Parser PARSER = new Parser();


    private void replaceRootExtractionOperationByNumber() {
        for (int i = 0; i < arithmeticOperationsFromMathExpression.size(); i++) {
            if (arithmeticOperationsFromMathExpression.get(i).contains("sqrt")) {
                numbersFromMathExpression.set(i + 1, sqrt(numbersFromMathExpression.get(i + 1)));
                arithmeticOperationsFromMathExpression
                        .set(i,
                                arithmeticOperationsFromMathExpression.get(i).replace("sqrt", ""));
            }
        }
    }

    private void calculateMultiplyOperations() throws IncorrectOperationEntryException {

        List<String> operationsForRemoving = new ArrayList<>();
        List<BigDecimal> numbersForRemoving = new ArrayList<>();

        for (int i = 0; i < arithmeticOperationsFromMathExpression.size(); i++) {
            if (arithmeticOperationsFromMathExpression.get(i).equals("*")) {
                numbersFromMathExpression.set(i + 1, multiply(numbersFromMathExpression.get(i), numbersFromMathExpression.get(i + 1)));
                operationsForRemoving.add(arithmeticOperationsFromMathExpression.get(i));
                numbersForRemoving.add(numbersFromMathExpression.get(i));
            } else if (arithmeticOperationsFromMathExpression.get(i).contains("/")) {
                if (numbersFromMathExpression.get(i + 1).compareTo(new BigDecimal("0")) == 0)
                    throw new DivisionByZeroException("Division by zero");
                else
                    numbersFromMathExpression.set(i + 1, division(numbersFromMathExpression.get(i), numbersFromMathExpression.get(i + 1)));
                operationsForRemoving.add(arithmeticOperationsFromMathExpression.get(i));
                numbersForRemoving.add(numbersFromMathExpression.get(i));
            }
            if (IncorrectOperationEntryException.notValidOperation(arithmeticOperationsFromMathExpression.get(i))) {
                throw new IncorrectOperationEntryException("Incorrect arithmetic operation");
            }
        }

        arithmeticOperationsFromMathExpression.removeAll(operationsForRemoving);
        numbersFromMathExpression.removeAll(numbersForRemoving);

    }

    private BigDecimal calculateAdditiveOperations() throws IncorrectOperationEntryException {

        BigDecimal resultCalculateAdditiveOperations = numbersFromMathExpression.get(0);

        for (int i = 0; i < arithmeticOperationsFromMathExpression.size(); i++) {
            if (arithmeticOperationsFromMathExpression.get(i).equals("+")) {
                resultCalculateAdditiveOperations = addition(resultCalculateAdditiveOperations, numbersFromMathExpression.get(i + 1));
            } else if (arithmeticOperationsFromMathExpression.get(i).contains("-")) {
                resultCalculateAdditiveOperations = subtraction(resultCalculateAdditiveOperations, numbersFromMathExpression.get(i + 1));
            }
            if (IncorrectOperationEntryException.notValidOperation(arithmeticOperationsFromMathExpression.get(i))) {
                throw new IncorrectOperationEntryException("Incorrect arithmetic operation");
            }
        }

        return resultCalculateAdditiveOperations;
    }

    private boolean firstMathExpressionsNumberIsNegative() {
        return mathExpression.charAt(0) == '-';
    }

    public void makeFirstMathExpressionsNumberNegative() {
        arithmeticOperationsFromMathExpression.remove(0);
        numbersFromMathExpression.set(0, numbersFromMathExpression.get(0).multiply(BigDecimal.valueOf(-1)));
    }

    public BigDecimal calculateMathExpression(String mathExpression) throws IncorrectOperationEntryException, DivisionByZeroException {

        this.mathExpression = mathExpression;
        this.arithmeticOperationsFromMathExpression = PARSER.extractionEverythingExceptNumbers(this.mathExpression);
        this.numbersFromMathExpression = PARSER.extractionNumbers(this.mathExpression);


        if (firstMathExpressionsNumberIsNegative()) {
            makeFirstMathExpressionsNumberNegative();
        }

        replaceRootExtractionOperationByNumber();
        calculateMultiplyOperations();

        return calculateAdditiveOperations();
    }

    public BigDecimal addition(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public BigDecimal subtraction(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    public BigDecimal division(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }

    public BigDecimal sqrt(BigDecimal a) {
        return BigDecimal.valueOf(Math.sqrt(a.doubleValue()));
    }
}
