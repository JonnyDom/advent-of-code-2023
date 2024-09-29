package com.advent.of.code.jpad.y2023d10.actors.part2;

import com.advent.of.code.jpad.y2023d10.actors.shared.Position;

import java.util.Set;

public record LaserReport(LaserEnd end, Set<Position> tilesTouchedByLaser) {
}
