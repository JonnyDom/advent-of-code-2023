package com.advent.of.code.jpad.y2023d10.actors.shared;

import com.advent.of.code.jpad.y2023d10.actors.part2.LaserEnd;
import com.advent.of.code.jpad.y2023d10.actors.part2.LaserReport;
import com.advent.of.code.jpad.y2023d10.actors.part2.LoopSide;

import java.util.*;
import java.util.function.Predicate;

public class Sketch {

    private final char[][] tileMap;
    private final Position animalPosition;

    private Sketch(char[][] tileMap, Position animalPosition) {
        this.tileMap = tileMap;
        this.animalPosition = animalPosition;
    }

    public static Sketch parseInput(List<String> inputTileMap) {
        char[][] tileMatrix = new char[inputTileMap.size()][inputTileMap.get(0).length()];
        Position animalPosition = null;
        for (int i = 0; i < inputTileMap.size(); i++) {
            for (int j = 0; j < inputTileMap.get(i).length(); j++) {
                tileMatrix[i][j] = inputTileMap.get(i).charAt(j);
                if (Tile.fromSymbol(tileMatrix[i][j]) == Tile.ANIMAL) {
                    animalPosition = new Position(j, i);
                }
            }
        }
        return new Sketch(tileMatrix, animalPosition);
    }

    public Direction computeStartingDirection() {
        return Arrays.stream(Direction.values())
                .filter(movementsWithinBoundaries())
                .filter(validPipes())
                .filter(directionToNextConnectingPipe())
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    private Predicate<Direction> directionToNextConnectingPipe() {
        return direction -> {
            Position possiblePositionFollowUp = animalPosition.nextPosition(direction);
            Pipe possiblePipeFollowUp = Pipe.fromSymbol(tileMap[possiblePositionFollowUp.y()][possiblePositionFollowUp.x()]);
            return possiblePipeFollowUp.getConnectingDirections().contains(direction.opposite());
        };
    }

    private Predicate<Direction> validPipes() {
        return direction -> {
            Position nextPossiblePosition = animalPosition.nextPosition(direction);
            Tile nextTile = Tile.fromSymbol(tileMap[nextPossiblePosition.y()][nextPossiblePosition.x()]);
            return nextTile == Tile.PIPE;
        };
    }

    private Predicate<Direction> movementsWithinBoundaries() {
        return direction -> !((animalPosition.x() == 0 && direction == Direction.WEST)
                || (animalPosition.x() == tileMap[0].length - 1 && direction == Direction.EAST)
                || (animalPosition.y() == 0 && direction == Direction.NORTH)
                || (animalPosition.y() == tileMap.length - 1 && direction == Direction.SOUTH));
    }

    public Position getAnimalPosition() {
        return animalPosition;
    }

    public char getTileOn(Position currentPosition) {
        return tileMap[currentPosition.y()][currentPosition.x()];
    }

    public Map<LoopSide, Set<LaserReport>> fireLasersFromPosition(LoopTile loopTile, Set<Position> loopPositions) {
        // we want to fire lasers from this tile and into all the directions to which this pipe does NOT connect to
        // e.g. in a '|', we want to fire a laser to east and west; in an 'F', west and north
        Set<Direction> laserDirections = new HashSet<>(Optional.ofNullable(loopTile.pipe())
                .map(Pipe::getNonConnectingDirections).orElse(Direction.all()));
        Map<LoopSide, Set<LaserReport>> nonLoopTilesHitByLasersMap = new HashMap<>();

        for (Direction laserDirection : laserDirections) {
            LoopSide sideToWhichLaserWasFired = LoopSide.computeFromPipeAndLaserDirections(loopTile.pipe(), loopTile.goingTo(), laserDirection);
            nonLoopTilesHitByLasersMap.computeIfAbsent(sideToWhichLaserWasFired, key -> new HashSet<>())
                            .add(fireLaser(loopTile.position(), laserDirection, loopPositions));
        }

        return nonLoopTilesHitByLasersMap;

    }

    private LaserReport fireLaser(Position position, Direction laserDirection, Set<Position> loopPositions) {
        Set<Position> positionsHitByLaser = new HashSet<>();

        LaserEnd laserEnd = null;
        Position positionHitByLaser = position;
        while (laserEnd == null) {
            positionHitByLaser = positionHitByLaser.nextPosition(laserDirection);
            if (loopPositions.contains(positionHitByLaser)) {
                laserEnd = LaserEnd.LOOP;
            } else if (outOfBoundaries(positionHitByLaser)) {
                laserEnd = LaserEnd.BOUNDARY;
            } else {
                positionsHitByLaser.add(positionHitByLaser);
            }
        }

        return new LaserReport(laserEnd, positionsHitByLaser);
    }

    private boolean outOfBoundaries(Position position) {
        return position.x() < 0 || position.x() >= tileMap[0].length || position.y() < 0 || position.y() >= tileMap.length;
    }
}
