package com.advent.of.code.jpad.y2023d4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.jpad.y2023d4.RecursiveScratchCardGame.scratchCardsAndTheirCopies;

public class Puzzle4 {
    public static void main(String[] args) throws IOException {
        // Part 1 - Takes ~ 30ms
        long startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d4.txt"))) {
            int sum = lines.map(Cards::parseLine).mapToInt(Card::countPoints).sum();
            System.out.println("Part 1: " + sum);
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");

        // Part 2 - Takes ~ 450ms
        startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d4.txt"))) {
            List<Card> scratchCards = lines.map(Cards::parseLine).toList();
            int sum = scratchCardsAndTheirCopies(scratchCards);
            System.out.println("Part 2: " + sum);
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms");
    }
}