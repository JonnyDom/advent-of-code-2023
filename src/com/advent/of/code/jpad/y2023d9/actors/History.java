package com.advent.of.code.jpad.y2023d9.actors;

import com.advent.of.code.jpad.utils.ParseableString;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class History {

    private final LinkedList<Integer> pastReadings;

    private History(LinkedList<Integer> pastReadings) {
        this.pastReadings = pastReadings;
    }

    public static History fromInput(String line) {
        LinkedList<Integer> readings = ParseableString.of(line)
                .valuesSeparatedByBlanks()
                .map(ParseableString::toIntValue)
                .collect(Collectors.toCollection(LinkedList::new));
        return new History(readings);
    }

    public Integer extrapolatePastValue() {
        return AnalysisProcessor.extrapolateFromHistory(pastReadings, AnalysisProcessor.ExtrapolationType.PAST);
    }

    public Integer extrapolateNextValue() {
        return AnalysisProcessor.extrapolateFromHistory(pastReadings, AnalysisProcessor.ExtrapolationType.FUTURE);
    }

}
