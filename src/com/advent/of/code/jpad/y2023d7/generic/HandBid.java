package com.advent.of.code.jpad.y2023d7.generic;

import com.advent.of.code.jpad.utils.ParseableString;
import com.advent.of.code.jpad.y2023d7.part1.Hand1;
import com.advent.of.code.jpad.y2023d7.part2.Hand2;

import java.util.List;

public class HandBid {
    private final Hand hand;
    private final Integer bidAmount;

    public static HandBid parseForPart1(String line) {
        List<String> values = getLines(line);
        return new HandBid(Hand1.fromLabel(values.get(0)), Integer.parseInt(values.get(1)));
    }

    public static HandBid parseForPart2(String line) {
        List<String> values = getLines(line);
        return new HandBid(Hand2.fromLabel(values.get(0)), Integer.parseInt(values.get(1)));
    }

    private static List<String> getLines(String line) {
        return ParseableString.of(line)
                .valuesSeparatedBy(" ")
                .map(ParseableString::toStringValue).toList();
    }

    private HandBid(Hand hand, Integer bidAmount) {
        this.hand = hand;
        this.bidAmount = bidAmount;
    }

    public Hand getHand() {
        return hand;
    }

    public Integer getBidAmount() {
        return bidAmount;
    }
}
