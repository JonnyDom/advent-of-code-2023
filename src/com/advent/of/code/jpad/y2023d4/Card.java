package com.advent.of.code.jpad.y2023d4;

import java.util.ArrayList;
import java.util.List;

public record Card(int cardNumber, List<Integer> winningNumbers, List<Integer> scratchNumbers) {

    /**
     * Solution to part 1
     * @return the number of points
     */
    public int countPoints() {
        return (int) Math.pow(2, getMatches() - 1);
    }

    /**
     * Solution to part 2
     * @param totalScore
     * @param totalCards
     * @param cardsToProcess
     * @return
     */
    public int countRecursiveScore(int totalScore, List<Card> totalCards, List<Card> cardsToProcess) {
        List<Card> newCardsToProcess = new ArrayList<>();
        for (Card cardToProcess : cardsToProcess) {
            int matches = cardToProcess.getMatches();
            if (cardNumber < totalCards.size()) {
                newCardsToProcess.addAll(totalCards.subList(cardNumber - 1, Math.max(cardNumber + matches - 1, totalCards.size() -1)));
            }
            totalScore += matches;
        }
        return newCardsToProcess.isEmpty() ? totalScore : countRecursiveScore(totalScore, totalCards, newCardsToProcess);
    }

    public int getMatches() {
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
