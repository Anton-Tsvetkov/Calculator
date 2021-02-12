package com.epam.laboratory.exceptions;

public class IncorrectOperationEntryException extends Exception {

    public IncorrectOperationEntryException(String message) {
        super(message);
    }

    public static boolean notValidOperation(String str){
        return str.length() > 1 && !str.contains("sqrt")
                || (!str.equals("*") && !str.equals("/") && !str.equals("+") && !str.equals("-"));
    }

}
