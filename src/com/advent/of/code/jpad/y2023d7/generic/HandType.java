package com.advent.of.code.jpad.y2023d7.generic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public enum HandType {
    FIVE_OF_A_KIND,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    THREE_OF_A_KIND,
    TWO_PAIR,
    ONE_PAIR,
    HIGH_CARD;

    private static final Map<Long, Function<Long, HandType>> typeByOccurrences = Map.of(
            5L, secondOccurrence -> FIVE_OF_A_KIND,
            4L, secondOccurrence -> FOUR_OF_A_KIND,
            3L, secondOccurrence -> secondOccurrence == 2 ? FULL_HOUSE : THREE_OF_A_KIND,
            2L, secondOccurrence -> secondOccurrence == 2 ? TWO_PAIR : ONE_PAIR,
            1L, secondOccurrence -> HIGH_CARD
    );

    public boolean isStrongerThan(HandType other) {
        return this.ordinal() < other.ordinal();
    }

    public static HandType fromRankOccurrences(List<Long> rankOccurrences) {
        List<Long> occurrencesSorted = rankOccurrences.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        return typeByOccurrences.get(occurrencesSorted.get(0)).apply(occurrencesSorted.size() > 1 ? occurrencesSorted.get(1) : null);
    }
}
