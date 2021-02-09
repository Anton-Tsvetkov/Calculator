package com.epam.laboratory;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    static List<Character> arithmeticOperations = new ArrayList<>();
    static List<Double> numbers = new ArrayList<>();

    static final int CODE_OF_NULL_NUMERIC = (int) '0';
    static final int CODE_OF_NINE_NUMERIC = (int) '9';


    public double calculateMathExpression(String mathExpression){

        return 0.0;
    }

    private static double addition(double a, double b) {
        return a + b;
    }

    private static double subtraction(double a, double b) {
        return a - b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double division(double a, double b) {
        return a / b;
    }

    private static double sqrt(double a) {
        return Math.sqrt(a);
    }
}
