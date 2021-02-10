package com.epam.laboratory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

    private String mathExpression;
    private List<BigDecimal> numbersFromMathExpression;
    private List<String> arithmeticOperationsFromMathExpression;

    public List<BigDecimal> extractionNumbersFromMathExpression(String mathExpression) {

        mathExpression = mathExpression.replaceAll(" ", "");
        String[] numbersArrayAsString = mathExpression.split("[^0-9.]");

        List<String> numbersListAsString = new ArrayList<>(Arrays.asList(numbersArrayAsString));
        numbersListAsString.removeIf(s -> s.length() == 0);

        List<BigDecimal> numbersListAsBigDecimal = new ArrayList<>();
        for (String s : numbersListAsString) {
            numbersListAsBigDecimal.add(new BigDecimal(s));
        }

        return numbersListAsBigDecimal;
    }

    public List<String> extractionArithmeticOperationsFromMathExpression(String mathExpression) {

        mathExpression = mathExpression.replaceAll(" ", "");
        String[] operationsArray = mathExpression.split("[0-9.]");

        List<String> operationsList = new ArrayList<>(Arrays.asList(operationsArray));
        operationsList.removeIf(s -> s.length() == 0);

        return operationsList;
    }

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

    private void calculateMultiplyOperations() {

        List<String> operationsForRemoving = new ArrayList<>();
        List<BigDecimal> numbersForRemoving = new ArrayList<>();

        for (int i = 0; i < arithmeticOperationsFromMathExpression.size(); i++) {
            if (arithmeticOperationsFromMathExpression.get(i).equals("*")) {
                numbersFromMathExpression.set(i + 1, multiply(numbersFromMathExpression.get(i), numbersFromMathExpression.get(i + 1)));
                operationsForRemoving.add(arithmeticOperationsFromMathExpression.get(i));
                numbersForRemoving.add(numbersFromMathExpression.get(i));
            } else if (arithmeticOperationsFromMathExpression.get(i).contains("/")) {
                numbersFromMathExpression.set(i + 1, division(numbersFromMathExpression.get(i), numbersFromMathExpression.get(i + 1)));
                operationsForRemoving.add(arithmeticOperationsFromMathExpression.get(i));
                numbersForRemoving.add(numbersFromMathExpression.get(i));
            }

        }

        arithmeticOperationsFromMathExpression.removeAll(operationsForRemoving);
        numbersFromMathExpression.removeAll(numbersForRemoving);
    }

    private BigDecimal calculateAdditiveOperations() {

        BigDecimal resultCalculateAdditiveOperations = numbersFromMathExpression.get(0);

        for (int i = 0; i < arithmeticOperationsFromMathExpression.size(); i++) {
            if (arithmeticOperationsFromMathExpression.get(i).equals("+")) {
                resultCalculateAdditiveOperations = addition(resultCalculateAdditiveOperations, numbersFromMathExpression.get(i + 1));
            } else if (arithmeticOperationsFromMathExpression.get(i).contains("-")) {
                resultCalculateAdditiveOperations = subtraction(resultCalculateAdditiveOperations, numbersFromMathExpression.get(i + 1));
            }
        }

        return resultCalculateAdditiveOperations;
    }

    private boolean firstMathExpressionsNumberIsNegative(){
        return mathExpression.charAt(0) == '-';
    }

    private void makeFirstMathExpressionsNumberNegative(){
        arithmeticOperationsFromMathExpression.remove(0);
        numbersFromMathExpression.set(0, numbersFromMathExpression.get(0).multiply(BigDecimal.valueOf(-1)));
    }

    public BigDecimal calculateMathExpression(String mathExpression) {

        this.mathExpression = mathExpression;
        this.arithmeticOperationsFromMathExpression = extractionArithmeticOperationsFromMathExpression(mathExpression);
        this.numbersFromMathExpression = extractionNumbersFromMathExpression(mathExpression);

        if(firstMathExpressionsNumberIsNegative()){
            makeFirstMathExpressionsNumberNegative();
        }

        replaceRootExtractionOperationByNumber();
        calculateMultiplyOperations();
        return calculateAdditiveOperations();
    }

    private static BigDecimal addition(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    private static BigDecimal subtraction(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    private static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    private static BigDecimal division(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }

    private static BigDecimal sqrt(BigDecimal a) {
        return BigDecimal.valueOf(Math.sqrt(a.doubleValue()));
    }
}
