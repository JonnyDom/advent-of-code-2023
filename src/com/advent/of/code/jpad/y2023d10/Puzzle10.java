package com.advent.of.code.jpad.y2023d10;

import com.advent.of.code.jpad.y2023d10.actors.LoopAnalyzer;
import com.advent.of.code.jpad.y2023d10.actors.Sketch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Puzzle10 {
    public static void main(String[] args) throws IOException {
        // Part 1 - Takes ~ 92 ms
        long startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d10.txt"))) {
            Sketch sketch = Sketch.parseInput(lines.toList());
            System.out.println(new LoopAnalyzer(sketch).computeFarthestDistanceFromAnimal());
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");

        //part 2 - Takes ~ 8 ms
        startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d10.txt"))) {
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");
    }
}