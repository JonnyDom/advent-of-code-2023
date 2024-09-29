package com.advent.of.code.jpad.y2023d10;

import com.advent.of.code.jpad.y2023d10.actors.shared.Sketch;
import com.advent.of.code.jpad.y2023d10.actors.part1.LoopDistanceAnalyzer;
import com.advent.of.code.jpad.y2023d10.actors.part2.LoopEnclosedTilesAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Puzzle10 {
    public static void main(String[] args) throws IOException {
        // Part 1 - Takes ~ 90 ms
        long startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d10.txt"))) {
            Sketch sketch = Sketch.parseInput(lines.toList());
            System.out.println(new LoopDistanceAnalyzer(sketch).compute());
        }
        System.out.println("Part 1 - Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");

        //part 2 - Takes ~ 160 ms
        startTime = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d10.txt"))) {
            Sketch sketch = Sketch.parseInput(lines.toList());
            System.out.println(new LoopEnclosedTilesAnalyzer(sketch).compute());
        }
        System.out.println("Part 2 - Solved in " + (System.currentTimeMillis() - startTime) + " ms\n");
    }
}