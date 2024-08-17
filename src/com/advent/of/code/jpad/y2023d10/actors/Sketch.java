package com.advent.of.code.jpad.y2023d10.actors;

import java.util.Arrays;
import java.util.List;
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
                .findFirst().orElseThrow(IllegalArgumentException::new).opposite();
    }

    private Predicate<Direction> directionToNextConnectingPipe() {
        return direction -> {
            Position possiblePositionFollowUp = animalPosition.nextPosition(direction);
            Pipe possiblePipeFollowUp = Pipe.fromSymbol(tileMap[possiblePositionFollowUp.y()][possiblePositionFollowUp.x()]);
            return possiblePipeFollowUp.getConnectingDirections().contains(direction);
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
}
