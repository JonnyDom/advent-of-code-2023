package com.advent.of.code.jpad.y2023d4;

import java.util.ArrayList;
import java.util.List;

public class RecursiveScratchCardGame {

    /**
     * Solution to part 2
     * @param totalCards the list of cards to scratch
     * @return the total number of resulting scratchcards
     */
    public static int scratchCardsAndTheirCopies(List<Card> totalCards) {
        return countRecursiveScore(totalCards.size(), totalCards, totalCards);
    }

    private static int countRecursiveScore(int totalScore, List<Card> cardsToProcess, List<Card> totalCards) {
        List<Card> newCardsToProcess = new ArrayList<>();
        for (Card cardToProcess : cardsToProcess) {
            int matches = cardToProcess.matches();
            if (cardToProcess.cardNumber() < totalCards.size() - 1) {
                newCardsToProcess.addAll(totalCards.subList(cardToProcess.cardNumber(), Math.min(cardToProcess.cardNumber() + matches, totalCards.size() -1)));
            }
            totalScore += matches;
        }
        return newCardsToProcess.isEmpty() ? totalScore : countRecursiveScore(totalScore, newCardsToProcess, totalCards);
    }
}
