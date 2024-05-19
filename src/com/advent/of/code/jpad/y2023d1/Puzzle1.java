package com.advent.of.code.jpad.y2023d1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Map<String, String> DIGITS_SPELLED_OUT = new HashMap<>();

    static {
        DIGITS_SPELLED_OUT.put("one", "1");
        DIGITS_SPELLED_OUT.put("two", "2");
        DIGITS_SPELLED_OUT.put("three", "3");
        DIGITS_SPELLED_OUT.put("four", "4");
        DIGITS_SPELLED_OUT.put("five", "5");
        DIGITS_SPELLED_OUT.put("six", "6");
        DIGITS_SPELLED_OUT.put("seven", "7");
        DIGITS_SPELLED_OUT.put("eight", "8");
        DIGITS_SPELLED_OUT.put("nine", "9");
    }

    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        long startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d1.txt"))) {
            int sum = lines.map(toPairOfFirstAndLastDigit())
                    .map(parsingTheDigits()).mapToInt(Integer::intValue).sum();
            System.out.println(sum);
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms");
    }

    private static Function<String, DigitPair> toPairOfFirstAndLastDigit() {
        return lineOfCharacters -> {
            String firstDigit = findDigitAsCharInString(lineOfCharacters, false);
            String lastDigit = findDigitAsCharInString(lineOfCharacters, true);
            System.out.println(lineOfCharacters + " - number: " + firstDigit + lastDigit);
            return DigitPair.of(firstDigit, lastDigit);
        };
    }

    private static String findDigitAsCharInString(String line, boolean reverseFind) {
        String potentialSpelledOut = "";
        int initialIndex = (reverseFind ? line.length() - 1 : 0);
        Predicate<Integer> stopCondition = (reverseFind ? i -> i >= 0 : i -> i < line.length());
        int increment = reverseFind ? -1 : 1;
        for (int i = initialIndex; stopCondition.test(i) ; i += increment) {
            char currentChar = line.charAt(i);
            if (Character.isDigit(currentChar)) { // the actual digit
                return String.valueOf(currentChar);
            }
            String currentString = reverseFind ? currentChar + potentialSpelledOut : potentialSpelledOut + currentChar;
            if (DIGITS_SPELLED_OUT.containsKey(currentString)) {
                return DIGITS_SPELLED_OUT.get(currentString);
            } else if (couldPotentiallyBeASpelledOutNumber(currentString)) {
                potentialSpelledOut = currentString;
            } else {
                potentialSpelledOut = removeCharFromPreviousIteration(currentString, reverseFind);
            }
        }
        throw new RuntimeException("Digit not found for line " + line + " when searching in "
                + (reverseFind ? "reverse" : "normal") + " order");
    }

    private static String removeCharFromPreviousIteration(String currentString, boolean reverseFind) {
        if (reverseFind) {
            return currentString.substring(0, currentString.length() - 1);
        } else {
            return currentString.substring(1);
        }
    }

    private static boolean couldPotentiallyBeASpelledOutNumber(String potentialNumber) {
        return DIGITS_SPELLED_OUT.keySet().stream().anyMatch(digit -> digit.contains(potentialNumber));
    }

    private static Function<DigitPair, Integer> parsingTheDigits() {
        return pair -> {
            String concatenatedNumber = pair.getLeftMost() + pair.getRightMost();
            return Integer.parseInt(concatenatedNumber);
        };
    }
}