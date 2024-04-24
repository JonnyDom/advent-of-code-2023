package com.advent.of.code.jpad.y2023d2;

import java.util.Arrays;

public enum Color {
    BLUE("blue"),
    RED("red"),
    GREEN("green");

    private String value;

    private Color(String value) {
        this.value = value;
    }

    public static Color fromString(String colorValue) {
        return Arrays.stream(values()).filter(color -> color.value.equals(colorValue))
                .findAny()
                .orElse(null);
    }
}
