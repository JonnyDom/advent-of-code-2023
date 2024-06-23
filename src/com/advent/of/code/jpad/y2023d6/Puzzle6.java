package com.advent.of.code.jpad.y2023d6;

import com.advent.of.code.jpad.utils.ParseableString;
import com.advent.of.code.jpad.y2023d6.actors.RaceRecord;
import com.advent.of.code.jpad.y2023d6.actors.RaceSheet;
import com.advent.of.code.jpad.y2023d6.analyzer.BoatPerformanceAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;


public class Puzzle6 {
    public static void main(String[] args) throws IOException {
        // Part 1 - takes ~ 12 ms
        long startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d6.txt"))) {
            List<String> inputLines = lines.toList();
            RaceSheet raceSheet = RaceSheet.of(inputLines.get(0), inputLines.get(1));
            System.out.println(raceSheet.getNumberOfWaysRaceCanBeBeaten());
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");

        //part 2 - takes ~ 16 ms
        startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d6.txt"))) {
            List<String> inputLines = lines.toList();
            long raceTime = ParseableString.of(inputLines.get(0))
                    .valueBetween("Time:", "")
                    .withContentReplaced(" ", "").toLongValue();
            long maxDistance = ParseableString.of(inputLines.get(1))
                    .valueBetween("Distance:", "")
                    .withContentReplaced(" ", "").toLongValue();
            System.out.println(BoatPerformanceAnalyzer.computeRecordBreakingChargeDurations(new RaceRecord(raceTime, maxDistance)));
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");
    }
}