package com.advent.of.code.jpad.y2023d4;

import com.advent.of.code.jpad.utils.ParseableString;

import java.util.List;

public class Card {

    private final int cardNumber;
    private final List<Integer> winningNumbers;
    private final List<Integer> scratchNumbers;
    private int points = 0;
    private int matches = 0;

    private Card(int cardNumber, List<Integer> winningNumbers, List<Integer> scratchNumbers) {
        this.cardNumber = cardNumber;
        this.winningNumbers = winningNumbers;
        this.scratchNumbers = scratchNumbers;
        countPoints();
    }

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
                .map(ParseableString::toStringValue)
                .map(Integer::parseInt)
                .toList();
    }

    private void countPoints() {
        matches = (int) scratchNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
        points = (int) Math.pow(2, matches - 1);
    }

    public int getPoints() {
        return points;
    }

    public int getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        return "Card{" + cardNumber +
                ", winningNumbers=" + winningNumbers +
                ", scratchNumbers=" + scratchNumbers +
                '}';
    }
}
