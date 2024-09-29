package com.advent.of.code.jpad.y2023d10.actors.shared;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Direction {
    NORTH("^"),
    SOUTH("v"),
    EAST(">"),
    WEST("<");

    // For debugging purposes
    private final String label;

    Direction(String label) {
        this.label = label;
    }

    public static Set<Direction> all() {
        return Arrays.stream(values()).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return label;
    }

    public Direction opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }
}
