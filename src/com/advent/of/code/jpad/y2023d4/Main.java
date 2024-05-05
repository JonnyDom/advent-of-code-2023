package com.advent.of.code.jpad.y2023d4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d4.txt"))) {
            int sum = lines.map(Card::parseLine).mapToInt(Card::getPoints).sum();
            System.out.println(sum);
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms");
    }
}