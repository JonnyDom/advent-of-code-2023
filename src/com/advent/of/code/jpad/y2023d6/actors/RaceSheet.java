package com.advent.of.code.jpad.y2023d6.actors;

import com.advent.of.code.jpad.utils.ParseableString;
import com.advent.of.code.jpad.y2023d6.analyzer.BoatPerformanceAnalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaceSheet {
    private final List<RaceRecord> raceRecords;

    private RaceSheet(List<RaceRecord> raceRecords) {
        this.raceRecords = raceRecords;
    }

    public static RaceSheet of(String timeInput, String distanceInput) {
        int[] times = ParseableString.of(timeInput)
                .valueBetween("Time:", "")
                .valuesSeparatedByBlanks()
                .mapToInt(ParseableString::toIntValue)
                .toArray();
        int[] maxDurations = ParseableString.of(distanceInput)
                .valueBetween("Distance:", "")
                .valuesSeparatedByBlanks()
                .mapToInt(ParseableString::toIntValue)
                .toArray();
        List<RaceRecord> raceRecords = new ArrayList<>();
        for (int i = 0; i < times.length ; i++) {
            raceRecords.add(new RaceRecord(times[i], maxDurations[i]));
        }
        return new RaceSheet(Collections.unmodifiableList(raceRecords));
    }

    public long getNumberOfWaysRaceCanBeBeaten() {
        return raceRecords.stream()
                .mapToLong(BoatPerformanceAnalyzer::computeRecordBreakingChargeDurations)
                .reduce((a,b) -> a * b).orElseThrow(RuntimeException::new);
    }

    @Override
    public String toString() {
        return "RaceSheet{" +
                "raceRecords=" + raceRecords +
                '}';
    }
}
