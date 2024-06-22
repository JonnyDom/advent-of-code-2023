package com.advent.of.code.jpad.y2023d7.part1;

import com.advent.of.code.jpad.y2023d7.generic.Rank;

import java.util.Arrays;

public enum Rank1 implements Rank {
    A('A'),
    K('K'),
    Q('Q'),
    J('J'),
    TEN('T'),
    NINE('9'),
    EIGHT('8'),
    SEVEN('7'),
    SIX('6'),
    FIVE('5'),
    FOUR('4'),
    THREE('3'),
    TWO('2');

    private final char label;

    Rank1(char label) {
        this.label = label;
    }

    public static Rank1 fromLabel(char label) {
        return Arrays.stream(values()).filter(rank -> rank.label == label)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isStrongerThan(Rank other) {
        if(other instanceof Rank1 otherRank) {
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
