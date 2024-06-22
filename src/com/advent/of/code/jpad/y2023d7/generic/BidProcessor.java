package com.advent.of.code.jpad.y2023d7.generic;

import java.util.Comparator;
import java.util.List;

public class BidProcessor {

    public static int process(List<HandBid> bids) {
        List<HandBid> sortedBids = bids.stream().sorted(Comparator.comparing(HandBid::getHand)).toList();
        int totalWinnings = 0;
        for (int i = 0; i < sortedBids.size(); i++) {
            totalWinnings += (i+1) * sortedBids.get(i).getBidAmount();
        }
        return totalWinnings;
    }
}
