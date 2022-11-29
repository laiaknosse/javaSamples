package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        System.out.println(calc(input));
    }

    public static String calc(String s) throws IOException {
        String[] numbers = s.split("[+\\-/*]");
        String operator;
        int numberIsRoman = 0;
        int result;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i].trim();
        }

        for (String n : numbers){
            if (n.contains("I") || n.contains("V") || n.contains("X")){
                numberIsRoman++;
            }
        }

        switch (numberIsRoman) {
            case 2:
                operator = s.replaceAll("[\\w]", "").trim();
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = String.valueOf(RomanToInteger.romanToInteger(numbers[i]));
                }
                break;
            case 0:
                operator = s.replaceAll("[\\d]", "").trim();
                break;
            default:
                throw new IOException("Can't operate Roman and Arabic");
        }


        for (String n : numbers){
            if (Integer.parseInt(n) > 10 || Integer.parseInt(n) < 1){
                throw new IOException("Numbers out of range(1-10, I-X)");
            }
        }
        if (numbers.length > 2 || operator.length() > 1){
            throw new IOException("Only one operator allowed");
        }

        switch (operator) {
            case "+":
                result = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
                break;
            case "-":
                result = Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[1]);
                break;
            case "/":
                result = Integer.parseInt(numbers[0]) / Integer.parseInt(numbers[1]);
                break;
            case "*":
                result = Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
                break;
            default:
                throw new IOException("Unknown operator");
        }


        if (numberIsRoman == 2){
            if (result < 1){
                throw new IOException("No negative in Roman");
            }
            else
                return RomanToInteger.intToRoman(result);
        }
        else
            return String.valueOf(result);
    }


}
