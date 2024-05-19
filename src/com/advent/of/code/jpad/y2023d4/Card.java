package com.advent.of.code.jpad.y2023d4;

import java.util.List;

public record Card(int cardNumber, List<Integer> winningNumbers, List<Integer> scratchNumbers, int matches) {

    public Card(int cardNumber, List<Integer> winningNumbers, List<Integer> scratchNumbers) {
        this(cardNumber, winningNumbers, scratchNumbers, computeMatches(scratchNumbers, winningNumbers));
    }

    /**
     * Solution to part 1
     * @return the number of points
     */
    public int countPoints() {
        return (int) Math.pow(2, matches - 1);
    }

    private static int computeMatches(List<Integer> scratchNumbers, List<Integer> winningNumbers) {
        return (int) scratchNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    @Override
    public String toString() {
        return "Card{" + cardNumber +
                ", winningNumbers=" + winningNumbers +
                ", scratchNumbers=" + scratchNumbers +
                '}';
    }
}
