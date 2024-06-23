package com.advent.of.code.jpad.y2023d9;

import com.advent.of.code.jpad.y2023d9.actors.OasisSensor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Puzzle9 {
    public static void main(String[] args) throws IOException {
        // Part 1 - Takes ~ 30 ms
        long startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d9.txt"))) {
            System.out.printf("Next values: %d %n", OasisSensor.fromInput(lines).sumOfExtrapolatedFutureValues());
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");

        //part 2 - Takes ~ 8 ms
        startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d9.txt"))) {
            System.out.printf("Past values: %d %n", OasisSensor.fromInput(lines).sumOfExtrapolatedPastValues());
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");
    }
}