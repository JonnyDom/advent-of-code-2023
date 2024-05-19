package com.advent.of.code.jpad.y2023d2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class Puzzle2 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        List<CubeCount> bagConfiguration = new ArrayList<>(3);
        bagConfiguration.add(CubeCount.of(Color.RED, 12));
        bagConfiguration.add(CubeCount.of(Color.GREEN, 13));
        bagConfiguration.add(CubeCount.of(Color.BLUE, 14));
        try (Stream<String> lines = Files.lines(Paths.get("input/y2023/input-d2.txt"))) {
//            int idsSum = lines.map(Game::fromGameLog)
//                    .filter(game -> game.isPossible(bagConfiguration))
//                    .peek(System.out::println)
//                    .mapToInt(Game::getGameNumber)
//                    .sum();
//            System.out.println(idsSum);
            int sum = lines.map(Game::fromGameLog)
                    .map(Game::getMinimumCubesByColor)
                    .mapToInt(numberOfCubesMultipliedTogether())
                    .sum();
            System.out.println(sum);
        }
        System.out.println("Solved in " + (System.currentTimeMillis() - startTime) + " ms");
    }

    private static ToIntFunction<Map<Color, Integer>> numberOfCubesMultipliedTogether() {
        return map -> map.values().stream().reduce((a,b) -> a * b).get();
    }
}