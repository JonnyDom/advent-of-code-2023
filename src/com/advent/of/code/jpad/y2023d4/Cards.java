package com.advent.of.code.jpad.y2023d4;

import com.advent.of.code.jpad.utils.ParseableString;

import java.util.List;

public class Cards {

    public static Card parseLine(String cardLine) {
        String cardNumber = ParseableString.of(cardLine).valueBetween("Card ", ":").toStringValue();
        List<String> numbersGroups = ParseableString.of(cardLine)
                .valueBetween(": ", "")
                .valuesSeparatedBy("\\|")
                .map(ParseableString::toStringValue)
                .toList();
        List<Integer> winningNumbers = parseGroupOfNumbers(numbersGroups.get(0));
        List<Integer> scratchNumbers = parseGroupOfNumbers(numbersGroups.get(1));
        return new Card(Integer.parseInt(cardNumber), winningNumbers, scratchNumbers);
    }

    private static List<Integer> parseGroupOfNumbers(String numbersString) {
        return ParseableString.of(numbersString)
                .valuesSeparatedBy(" ")
                .filter(ParseableString::nonBlank)
                .map(ParseableString::toIntValue)
                .toList();
    }
}
