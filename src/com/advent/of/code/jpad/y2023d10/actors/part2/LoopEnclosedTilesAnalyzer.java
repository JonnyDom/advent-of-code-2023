package com.advent.of.code.jpad.y2023d10.actors.part2;

import com.advent.of.code.jpad.y2023d10.actors.shared.LoopAnalyzer;
import com.advent.of.code.jpad.y2023d10.actors.shared.LoopTile;
import com.advent.of.code.jpad.y2023d10.actors.shared.Position;
import com.advent.of.code.jpad.y2023d10.actors.shared.Sketch;

import java.util.*;
import java.util.stream.Collectors;

public class LoopEnclosedTilesAnalyzer extends LoopAnalyzer {

    public LoopEnclosedTilesAnalyzer(Sketch sketch) {
        super(sketch);
    }

    public int compute() {
        // First get the loop
        LinkedList<LoopTile> loop = computeLoop();
        Set<Position> loopPositions = loop.stream().map(LoopTile::position).collect(Collectors.toSet());

        Map<LoopSide, Boolean> isSideExteriorMap = new HashMap<>(Map.of(LoopSide.LEFT, false, LoopSide.RIGHT, false));
        Map<LoopSide, Set<Position>> nonLoopTilesBySide = new HashMap<>();

        // Then in each pipe tile of the loop, "fire a laser" in the relevant directions
        // to detect all non loop tiles until the "laser" touches the loop again or the sketch boundaries
        for (LoopTile loopTile : loop) {
            Map<LoopSide, Set<LaserReport>> firedLasers = sketch.fireLasersFromPosition(loopTile, loopPositions);
            firedLasers.forEach((side, laserReports) -> {
                // marking the "outside" of the loop if any of the lasers hit the boundary
                if (laserReports.stream().anyMatch(report -> report.end() == LaserEnd.BOUNDARY)) {
                    isSideExteriorMap.put(side, true);
                    if (isSideExteriorMap.get(LoopSide.LEFT) && isSideExteriorMap.get(LoopSide.RIGHT)) {
                        throw new IllegalStateException("Cannot consider both sides exterior - problem detected on " + loopTile);
                    }
                }
                nonLoopTilesBySide.computeIfAbsent(side, key -> new HashSet<>()).addAll(laserReports.stream()
                                .map(LaserReport::tilesTouchedByLaser)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toSet()));
            });
        }

        return nonLoopTilesBySide.entrySet().stream()
                .filter(entry -> !isSideExteriorMap.get(entry.getKey()))
                .findFirst().orElseThrow(IllegalStateException::new)
                .getValue().size();
    }
}
