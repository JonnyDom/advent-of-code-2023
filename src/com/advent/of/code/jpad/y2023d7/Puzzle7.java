package com.advent.of.code.jpad.y2023d7;

import com.advent.of.code.jpad.y2023d7.generic.BidProcessor;
import com.advent.of.code.jpad.y2023d7.generic.HandBid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class Puzzle7 {
    public static void main(String[] args) throws IOException {
        // Part 1 - Takes ~ 60ms
        long startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d7.txt"))) {
            int totalWinnings = BidProcessor.process(lines.map(HandBid::parseForPart1).toList());
            System.out.println(totalWinnings);
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");

        //part 2 - Takes ~ 30ms
        startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d7.txt"))) {
            int totalWinnings = BidProcessor.process(lines.map(HandBid::parseForPart2).toList());
            System.out.println(totalWinnings);
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");
    }
}