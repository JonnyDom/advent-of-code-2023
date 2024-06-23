package com.advent.of.code.jpad.y2023d9.actors;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class OasisSensor {

    public static final Function<History, Integer> EXTRAPOLATE_PAST_VALUE = History::extrapolatePastValue;
    public static final Function<History, Integer> EXTRAPOLATE_NEXT_VALUE = History::extrapolateNextValue;
    private final List<History> report;

    public OasisSensor(List<History> report) {
        this.report = report;
    }

    public static OasisSensor fromInput(Stream<String> inputLines) {
        return new OasisSensor(inputLines.map(History::fromInput).toList());
    }

    public Integer sumOfExtrapolatedFutureValues(){
        return getSumOfExtrapolatedValues(EXTRAPOLATE_NEXT_VALUE);
    }

    public Integer sumOfExtrapolatedPastValues(){
        return getSumOfExtrapolatedValues(EXTRAPOLATE_PAST_VALUE);
    }

    private Integer getSumOfExtrapolatedValues(Function<History, Integer> extrapolationType) {
        return report.stream()
                .map(extrapolationType)
                .reduce(Integer::sum)
                .orElseThrow(() -> new RuntimeException("Next values could not be computed"));
    }
}
