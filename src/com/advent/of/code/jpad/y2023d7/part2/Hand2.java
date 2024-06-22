package com.advent.of.code.jpad.y2023d7.part2;

import com.advent.of.code.jpad.y2023d7.generic.Card;
import com.advent.of.code.jpad.y2023d7.generic.Hand;
import com.advent.of.code.jpad.y2023d7.generic.HandType;
import com.advent.of.code.jpad.y2023d7.generic.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Hand2 extends Hand {

    public static Hand fromLabel(String handLabel) {
        if (handLabel.length() != 5) {
            throw new IllegalArgumentException("Hand input must have 5 characters");
        }
        List<Card> cards = IntStream.range(0, 5)
                .mapToObj(handLabel::charAt)
                .map(Rank2::fromLabel)
                .map(Card::of)
                .toList();
        return new Hand2(cards);
    }

    private Hand2(List<Card> cards) {
        super(cards, computeFromCards(cards));
    }

    private static HandType computeFromCards(List<Card> hand) {
        Map<Rank, Long> rankOccurrencesGrouped = hand.stream()
                .collect(groupingBy(Card::getRank, counting()));
        HandType handType = HandType.fromRankOccurrences(rankOccurrencesGrouped.values().stream().toList());

        if (rankOccurrencesGrouped.containsKey(Rank2.JOKER)) {
            List<RankOccurrence> rankOccurrences = rankOccurrencesGrouped.entrySet().stream()
                    .map(entry -> new RankOccurrence((Rank2) entry.getKey(), entry.getValue()))
                    .toList();
            for(RankOccurrence rankOccurrence : rankOccurrences) {
                if (rankOccurrence.rank() != Rank2.JOKER) {
                    Map<Rank, Long> rankOccurrencesGroupedAdjusted = new HashMap<>(Map.copyOf(rankOccurrencesGrouped));
                    rankOccurrencesGroupedAdjusted.merge(rankOccurrence.rank(), rankOccurrencesGrouped.get(Rank2.JOKER), Long::sum);
                    rankOccurrencesGroupedAdjusted.put(Rank2.JOKER, 0L);
                    HandType adjustedHandType = HandType.fromRankOccurrences(rankOccurrencesGroupedAdjusted.values().stream().toList());
                    if (adjustedHandType.isStrongerThan(handType)) {
                        handType = adjustedHandType;
                    }
                }
            }
        }
        return handType;
    }
}
