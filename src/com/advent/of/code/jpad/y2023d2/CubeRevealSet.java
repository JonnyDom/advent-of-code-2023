package com.advent.of.code.jpad.y2023d2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CubeRevealSet {

    public Map<Color, Integer> getCubeCounts() {
        return cubeCounts;
    }

    private final Map<Color, Integer> cubeCounts;

    private CubeRevealSet(Map<Color, Integer> cubeCounts) {
        this.cubeCounts = cubeCounts;
    }

    public static CubeRevealSet fromRevealLog(String log) {
        return new CubeRevealSet(Arrays.stream(log.split(", "))
                .map(String::trim)
                .map(CubeCount::fromCubeLog)
                .collect(Collectors.toConcurrentMap(CubeCount::getColor, CubeCount::getAmount)));
    }

    public boolean allowsConfiguration(List<CubeCount> bagConfiguration) {
        return cubeCounts.entrySet()
                .stream()
                .noneMatch(anNonExistentCubeColorOrLessThanTheExistingAmount(bagConfiguration));
    }

    private static Predicate<Map.Entry<Color, Integer>> anNonExistentCubeColorOrLessThanTheExistingAmount(List<CubeCount> bagConfiguration) {
        return (cubeRetrieval) -> {
            CubeCount cubesOfAGivenColorInTheBag = bagConfiguration.stream()
                    .filter(cubesOfColor -> cubesOfColor.getColor() == cubeRetrieval.getKey())
                    .findAny()
                    .orElse(null);
            return cubesOfAGivenColorInTheBag == null || cubesOfAGivenColorInTheBag.getAmount() < cubeRetrieval.getValue();
        };
    }

    @Override
    public String toString() {
        return "CubeRevealSet{" +
                "cubeCounts=" + cubeCounts +
                '}';
    }
}
