package com.advent.of.code.jpad.y2023d7.generic;

import com.advent.of.code.jpad.y2023d7.part2.Hand2;

import java.util.List;

public abstract class Hand implements Comparable<Hand> {

    private final List<Card> cards;
    private final HandType handType;

    protected Hand(List<Card> cards, HandType handType) {
        this.cards = cards;
        this.handType = handType;
    }

    public int compareTo(Hand otherHand) {
        if (handType.isStrongerThan(otherHand.getHandType())) {
            return 1;
        } else if (otherHand.getHandType().isStrongerThan(handType)) {
            return -1;
        } else {
            return compareCardsValues(otherHand);
        }
    }

    protected int compareCardsValues(Hand otherHand) {
        for (int i = 0; i < getCards().size(); i++) {
            Rank thisCardRank = getCards().get(i).getRank();
            Rank otherCardRank = otherHand.getCards().get(i).getRank();
            if (thisCardRank.isStrongerThan(otherCardRank)) {
                return 1;
            } else if (otherCardRank.isStrongerThan(thisCardRank)) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Hand{" + cards + ", type:" + handType + '}';
    }

    public List<Card> getCards() {
        return cards;
    }

    public HandType getHandType() {
        return handType;
    }
}
