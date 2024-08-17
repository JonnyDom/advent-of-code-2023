package com.advent.of.code.jpad.y2023d10.actors;

public record Position(int x, int y) {

    Position nextPosition(Direction direction) {
        int deltaX = switch (direction) {
            case EAST -> 1;
            case WEST -> -1;
            default -> 0;
        };
        int deltaY = switch (direction) {
            case NORTH -> -1;
            case SOUTH -> 1;
            default -> 0;
        };
        return new Position(x + deltaX, y + deltaY);
    }
}
