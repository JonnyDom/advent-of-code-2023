package com.advent.of.code.jpad.y2023d10.actors.shared;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public enum Pipe {

    VERTICAL('|', Set.of(Direction.NORTH, Direction.SOUTH)),
    HORIZONTAL('-', Set.of(Direction.WEST, Direction.EAST)),
    NE_BEND('L', Set.of(Direction.NORTH, Direction.EAST)),
    NW_BEND('J', Set.of(Direction.NORTH, Direction.WEST)),
    SW_BEND('7', Set.of(Direction.SOUTH, Direction.WEST)),
    SE_BEND('F', Set.of(Direction.SOUTH, Direction.EAST));

    private final char label;
    private final Set<Direction> connectingDirections;

    Pipe(char label, Set<Direction> connectingDirections) {
        this.label = label;
        this.connectingDirections = connectingDirections;
    }

    public static Pipe fromSymbol(char symbol) {
        return Arrays.stream(values())
                .filter(p -> p.label == symbol)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public static Pipe fromPreviousAndFollowingPipes(Direction previous, Direction following) {
        return Arrays.stream(values())
                .filter(pipe -> pipe.connectingDirections.contains(previous.opposite())
                        && pipe.connectingDirections.contains(following))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Direction getNextDirectionAfterComingFrom(Direction direction) {
        return Set.of(Pipe.HORIZONTAL, Pipe.VERTICAL).contains(this) ? direction
                : connectingDirections.stream()
                .filter(not(direction.opposite()::equals))
                .findFirst()
                .orElseThrow();
    }

    public Set<Direction> getConnectingDirections() {
        return connectingDirections;
    }

    public Set<Direction> getNonConnectingDirections() {
        return Arrays.stream(Direction.values())
                .filter(direction -> !connectingDirections.contains(direction))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return String.valueOf(label);
    }
}
