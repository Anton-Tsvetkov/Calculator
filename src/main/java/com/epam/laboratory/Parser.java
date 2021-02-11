package com.epam.laboratory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    //             if (operation.length() != 1 && !operation.contains("sqrt")) {
    //                throw new IncorrectOperationEntryException("Incorrect arithmetic operation");
    //            }


    public String deleteSpaces(String str){
        return str.replaceAll(" ", "");
    }

    public List<BigDecimal> extractionNumbers(String str){

        String[] numbersArrayAsString = str.split("[^0-9.]");

        List<String> numbersListAsString = new ArrayList<>(Arrays.asList(numbersArrayAsString));
        numbersListAsString.removeIf(s -> s.length() == 0);

        List<BigDecimal> numbersListAsBigDecimal = new ArrayList<>();
        for (String s : numbersListAsString) {
            numbersListAsBigDecimal.add(new BigDecimal(s));
        }

        return numbersListAsBigDecimal;

    }


    public List<String> extractionEverythingExceptNumbers(String str){

        String[] operationsArray = str.split("[0-9.]");

        List<String> operationsList = new ArrayList<>(Arrays.asList(operationsArray));
        operationsList.removeIf(s -> s.length() == 0);

        return operationsList;
    }



}
