package com.advent.of.code.jpad.y2023d7.generic;

public class Card implements Comparable<Card> {
    private final Rank rank;

    private Card(Rank rank) {
        this.rank = rank;
    }

    public static Card of(Rank rank) {
        return new Card(rank);
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public int compareTo(Card o) {
        if (rank.isStrongerThan(o.rank)) {
            return 1;
        } else if (o.rank.isStrongerThan( rank)) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return rank.getName();
    }
}
