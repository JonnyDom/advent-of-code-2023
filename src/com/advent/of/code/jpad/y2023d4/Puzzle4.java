package com.advent.of.code.jpad.y2023d4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.advent.of.code.jpad.y2023d4.RecursiveScratchCardGame.scratchCardsAndTheirCopies;

public class Main {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d4.txt"))) {
            int sum = lines.map(Cards::parseLine).mapToInt(Card::countPoints).sum();
            System.out.println("Part 1:" + sum);
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");

        startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d4.txt"))) {
            List<Card> scratchCards = lines.map(Cards::parseLine).toList();
            int sum = scratchCardsAndTheirCopies(scratchCards);
            System.out.println("Part 2 (imperative approach):" + sum);
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms");
    }
}