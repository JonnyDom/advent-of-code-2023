package com.advent.of.code.jpad.y2023d10.actors.shared;

public record LoopTile(Position position, Pipe pipe, Direction goingTo) {

    @Override
    public String toString() {
        return position + ":" + pipe;
    }
}
