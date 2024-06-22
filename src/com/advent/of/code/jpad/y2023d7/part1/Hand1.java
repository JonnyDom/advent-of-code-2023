package com.advent.of.code.jpad.y2023d7.part1;

import com.advent.of.code.jpad.y2023d7.generic.Card;
import com.advent.of.code.jpad.y2023d7.generic.Hand;
import com.advent.of.code.jpad.y2023d7.generic.HandType;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Hand1 extends Hand {

    public static Hand1 fromLabel(String handLabel) {
        if (handLabel.length() != 5) {
            throw new IllegalArgumentException("Hand input must have 5 characters");
        }
        List<Card> cards = IntStream.range(0, 5)
                .mapToObj(handLabel::charAt)
                .map(Rank1::fromLabel)
                .map(Card::of)
                .toList();
        return new Hand1(cards);
    }

    protected Hand1(List<Card> cards) {
        super(cards, computeFromCards(cards));
    }

    private static HandType computeFromCards(List<Card> hand) {
        List<Long> rankOccurrences = hand.stream()
                .collect(groupingBy(Card::getRank, counting()))
                .values().stream().toList();

        return HandType.fromRankOccurrences(rankOccurrences);
    }
}
