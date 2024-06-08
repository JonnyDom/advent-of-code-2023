package com.advent.of.code.jpad.y2023d5.generic;

import com.advent.of.code.jpad.utils.ParseableString;

import java.util.List;

public class MapRecord {

    private final long destinationRangeStart;
    private final long sourceRangeStart;
    private final long rangeLength;

    private MapRecord(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;
    }

    public static MapRecord fromLine(String mapLine) {
        List<Long> numbers = ParseableString.of(mapLine).valuesSeparatedBy(" ")
                .map(ParseableString::toStringValue)
                .map(Long::parseLong).toList();
        return new MapRecord(numbers.get(0), numbers.get(1), numbers.get(2));
    }

    public boolean containsSourceValueInRange(long sourceValue) {
        return sourceValue >= sourceRangeStart && sourceValue <= sourceRangeStart + rangeLength;
    }

    public long toDestinationValue(long sourceValue) {
        if (containsSourceValueInRange(sourceValue)) {
            return destinationRangeStart + (sourceValue - sourceRangeStart);
        } else {
            throw new IllegalArgumentException(sourceValue + " is not in range of record " + this);
        }
    }

    @Override
    public String toString() {
        return "MapRecord{" +
                "destinationRangeStart=" + destinationRangeStart +
                ", sourceRangeStart=" + sourceRangeStart +
                ", rangeLength=" + rangeLength +
                '}';
    }
}
