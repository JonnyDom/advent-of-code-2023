package com.advent.of.code.jpad.y2023d5.generic;

public enum Category {
    LOCATION(null),
    HUMIDITY(LOCATION),
    TEMPERATURE(HUMIDITY),
    LIGHT(TEMPERATURE),
    WATER(LIGHT),
    FERTILIZER(WATER),
    SOIL(FERTILIZER),
    SEED(SOIL);

    private final Category mappedCategory;

    Category(Category mappedCategory) {
        this.mappedCategory = mappedCategory;
    }

    public Category getMappedCategory() {
        return mappedCategory;
    }
}
