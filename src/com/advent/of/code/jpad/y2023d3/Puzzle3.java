package com.advent.of.code.jpad.y2023d3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle3 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d3.txt"))) {
            List<SchematicLine> schematicLines = lines.map(EngineSchematicParser::parseLine).collect(Collectors.toList());
            EngineSchematic schematic = new EngineSchematic(schematicLines);

            int sumOfPartNumbers = schematic.processToReturnEnginePartNumbers()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            System.out.println("sum of all of the part numbers=" + sumOfPartNumbers);

            int sumOfGearRatios = schematic.processToReturnGearRatios()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            System.out.println("sum of all of the gear ratios=" + sumOfGearRatios);
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms");
    }
}