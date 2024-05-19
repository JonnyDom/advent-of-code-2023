package com.advent.of.code.jpad.y2023d4;

import java.util.ArrayList;
import java.util.List;

public class RecursiveScratchCardGame {

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
            if (cardToProcess.cardNumber() < totalCards.size()) {
                newCardsToProcess.addAll(totalCards.subList(cardToProcess.cardNumber() - 1, Math.max(cardToProcess.cardNumber() + matches - 1, totalCards.size() -1)));
            }
            totalScore += matches;
        }
        return newCardsToProcess.isEmpty() ? totalScore : countRecursiveScore(totalScore, totalCards, newCardsToProcess);
    }
}
