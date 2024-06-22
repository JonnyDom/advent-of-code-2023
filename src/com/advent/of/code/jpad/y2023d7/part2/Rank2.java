package com.advent.of.code.jpad.y2023d7.part2;

import com.advent.of.code.jpad.y2023d7.generic.Rank;

import java.util.Arrays;

public enum Rank2 implements Rank {
    A('A'),
    K('K'),
    Q('Q'),
    TEN('T'),
    NINE('9'),
    EIGHT('8'),
    SEVEN('7'),
    SIX('6'),
    FIVE('5'),
    FOUR('4'),
    THREE('3'),
    TWO('2'),
    JOKER('J');

    private final char label;

    Rank2(char label) {
        this.label = label;
    }

    public static Rank2 fromLabel(char label) {
        return Arrays.stream(values()).filter(rank -> rank.label == label)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean isStrongerThan(Rank other) {
        if(other instanceof Rank2 otherRank) {
            return this.ordinal() < otherRank.ordinal();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getName() {
        return name();
    }
}
