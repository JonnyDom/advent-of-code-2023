package com.advent.of.code.jpad.y2023d2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    private final int gameNumber;
    private final List<CubeRevealSet> setsOfCubesRevealed;

    private Game(int gameNumber, List<CubeRevealSet> setsOfCubesRevealed) {
        this.gameNumber = gameNumber;
        this.setsOfCubesRevealed = setsOfCubesRevealed;
    }

    public static Game fromGameLog(String gameLog) {
        int gameNumber = Integer.parseInt(gameLog.substring(5, gameLog.indexOf(":")));
        String gameRecord = gameLog.substring(gameLog.indexOf(": ") + 2).trim();
        String[] cubeRevealsLogs = gameRecord.split("; ");
        List<CubeRevealSet> cubeCounts = Arrays.stream(cubeRevealsLogs)
                .map(CubeRevealSet::fromRevealLog)
                .collect(Collectors.toList());
        return new Game(gameNumber, cubeCounts);
    }

    public boolean isPossible(List<CubeCount> bagConfiguration) {
        return setsOfCubesRevealed.stream().allMatch(cubeSet -> cubeSet.allowsConfiguration(bagConfiguration));
    }

    public Map<Color, Integer> getMinimumCubesByColor() {
        Map<Color, Integer> minimumMap = new HashMap<>();
        setsOfCubesRevealed.forEach(cubesRevealed -> {
            cubesRevealed.getCubeCounts().forEach((color, amount) -> {
                minimumMap.merge(color, amount, Math::max);
            });
        });
        return minimumMap;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameNumber=" + gameNumber +
                ", cubeRevealSets=" + setsOfCubesRevealed +
                '}';
    }
}
