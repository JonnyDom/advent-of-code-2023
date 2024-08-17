package com.advent.of.code.jpad.y2023d10.actors;

import java.util.Set;

public enum Direction {
    NORTH("^"),
    SOUTH("v"),
    EAST(">"),
    WEST("<");

    private final String label;

    Direction(String label) {
        this.label = label;
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
