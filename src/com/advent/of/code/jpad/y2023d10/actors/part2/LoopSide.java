package com.advent.of.code.jpad.y2023d10.actors.part2;

import com.advent.of.code.jpad.y2023d10.actors.shared.Direction;
import com.advent.of.code.jpad.y2023d10.actors.shared.Pipe;

public enum LoopSide {
    LEFT,
    RIGHT;

    public static LoopSide computeFromPipeAndLaserDirections(Pipe pipe, Direction loopDirection, Direction laserDirection) {
        return switch (pipe) {
            case VERTICAL -> {
                if ((loopDirection == Direction.NORTH && laserDirection == Direction.EAST)
                    || (loopDirection == Direction.SOUTH && laserDirection == Direction.WEST)) {
                    yield RIGHT;
                } else {
                    yield LEFT;
                }
            }
            case HORIZONTAL -> {
                if ((loopDirection == Direction.WEST && laserDirection == Direction.NORTH)
                        || (loopDirection == Direction.EAST && laserDirection == Direction.SOUTH)) {
                    yield RIGHT;
                } else {
                    yield LEFT;
                }
            }
            case NE_BEND -> {
                if ((loopDirection == Direction.NORTH && laserDirection == Direction.WEST)
                        || (loopDirection == Direction.NORTH && laserDirection == Direction.SOUTH)) {
                    yield LEFT;
                } else {
                    yield RIGHT;
                }
            }
            case NW_BEND -> {
                if ((loopDirection == Direction.NORTH && laserDirection == Direction.EAST)
                        || (loopDirection == Direction.NORTH && laserDirection == Direction.SOUTH)) {
                    yield RIGHT;
                } else {
                    yield LEFT;
                }
            }
            case SW_BEND -> {
                if ((loopDirection == Direction.WEST && laserDirection == Direction.NORTH)
                        || (loopDirection == Direction.WEST && laserDirection == Direction.EAST)) {
                    yield RIGHT;
                } else {
                    yield LEFT;
                }
            }
            case SE_BEND -> {
                if ((loopDirection == Direction.EAST && laserDirection == Direction.NORTH)
                        || (loopDirection == Direction.EAST && laserDirection == Direction.WEST)) {
                    yield LEFT;
                } else {
                    yield RIGHT;
                }
            }
        };
    }
}
