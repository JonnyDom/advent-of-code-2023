package com.advent.of.code.jpad.y2023d5;

import com.advent.of.code.jpad.y2023d5.part1.SpecifiedSeedsAlmanac;
import com.advent.of.code.jpad.y2023d5.part2.SeedRangesAlmanac;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Puzzle5 {
    public static void main(String[] args) throws IOException {
        // Part 1 - takes ~ 30 ms
        long startTime = System.currentTimeMillis();

        String input = Files.readString(Paths.get("input/y2023/input-d5.txt"));
        long lowestLocation = SpecifiedSeedsAlmanac.of(input).getSeedLocations().stream()
                .mapToLong(Long::longValue)
                .min().orElseThrow(() -> new RuntimeException("No locations Found"));
        System.out.println("Lowest location number (Part 1): " + lowestLocation);
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");

        // Part 2 - takes ~ 50 ms
        startTime = System.currentTimeMillis();
        lowestLocation = SeedRangesAlmanac.of(input).getSeedRangeMinLocations().stream()
                .mapToLong(Long::longValue)
                .min().orElseThrow(() -> new RuntimeException("No locations Found"));
        System.out.println("Lowest location number (Part 2): " + lowestLocation);
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");
    }
}