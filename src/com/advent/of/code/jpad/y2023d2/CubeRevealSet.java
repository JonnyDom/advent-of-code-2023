package com.advent.of.code.jpad.y2023d2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class CubeRevealSet {

    public Map<Color, Integer> getCubeCounts() {
        return cubeCounts;
    }

    private Map<Color, Integer> cubeCounts;

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
        AtomicBoolean allows = new AtomicBoolean(true);
        cubeCounts.forEach((color, amount) -> {
            CubeCount cubesOfAGivenColorInTheBag = bagConfiguration.stream().filter(cubesOfColor -> cubesOfColor.getColor() == color).findAny().orElse(null);
            if (cubesOfAGivenColorInTheBag == null || cubesOfAGivenColorInTheBag.getAmount() < amount) {
                allows.set(false);
            }
        });
        return allows.get();
    }

    @Override
    public String toString() {
        return "CubeRevealSet{" +
                "cubeCounts=" + cubeCounts +
                '}';
    }
}
