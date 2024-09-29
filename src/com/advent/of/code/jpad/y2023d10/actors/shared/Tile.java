package com.advent.of.code.jpad.y2023d10.actors.shared;

import java.util.Arrays;
import java.util.Set;

public enum Tile {
    ANIMAL(Set.of('S')),
    PIPE(Set.of('|', '-', 'L', 'J', '7', 'F')),
    GROUND(Set.of('.'));

    private final Set<Character> variations;

    Tile(Set<Character> variations) {
        this.variations = variations;
    }

    public static Tile fromSymbol(char symbol) {
        return Arrays.stream(values())
                .filter(t -> t.variations.contains(symbol))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
