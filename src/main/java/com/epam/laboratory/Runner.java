package com.epam.laboratory;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Print math expression: ");

        String mathExpression = scanner.nextLine();

        Calculator calculator = new Calculator();
        System.out.print("Answer: " + calculator.calculateMathExpression(mathExpression));
    }
}
