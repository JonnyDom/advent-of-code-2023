package com.advent.of.code.jpad.y2023d8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class Puzzle8 {
    public static void main(String[] args) throws IOException {
        // Part 1
        long startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d8.txt"))) {
            System.out.println(NodeMap.parseInput(lines).countStepsNeededToReachEndNode());
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");

        //part 2
        startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d8.txt"))) {

        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");
    }
}