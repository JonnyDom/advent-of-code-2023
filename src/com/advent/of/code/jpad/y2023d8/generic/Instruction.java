package com.advent.of.code.jpad.y2023d8.generic;

import java.util.Arrays;

public enum Instruction {
    LEFT('L'),
    RIGHT('R');

    private final char label;

    Instruction(char label) {
        this.label = label;
    }

    public static Instruction fromLabel(int label) {
        return Arrays.stream(values())
                .filter(value -> value.label == label)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);//(NDS, FTV) (FTV, NDS)
    }
}
