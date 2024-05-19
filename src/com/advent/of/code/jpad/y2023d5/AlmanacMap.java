package com.advent.of.code.jpad.y2023d5;

import com.advent.of.code.jpad.utils.ParseableString;

import java.util.List;

public class AlmanacMap {

    private final Category sourceCategory;
    private final List<MapRecord> mapRecords;

    private AlmanacMap(Category sourceCategory, List<MapRecord> mapRecords) {
        this.sourceCategory = sourceCategory;
        this.mapRecords = mapRecords;
    }

    public static AlmanacMap fromMapBlock(String mapBlock) {
        List<String> mapLines = ParseableString.of(mapBlock)
                .valuesSeparatedBy("\r\n")
                .map(ParseableString::toStringValue).toList();
        Category sourceCategory = Category.valueOf(ParseableString.of(mapLines.get(0)).valueBetween("", "-").toStringValue().toUpperCase());
        List<MapRecord> mapRecords = mapLines.stream()
                .skip(1) //skip map title
                .map(MapRecord::fromLine)
                .toList();
        return new AlmanacMap(sourceCategory, mapRecords);
    }

    public long getDestinationFromSource(long sourceValue) {
        return mapRecords.stream()
                .filter(mapRecord -> mapRecord.containsSourceValueInRange(sourceValue))
                .findFirst()
                .map(mapRecord -> mapRecord.toDestinationValue(sourceValue))
                .orElse(sourceValue);
    }

    public Category getSourceCategory() {
        return sourceCategory;
    }

    @Override
    public String toString() {
        return "AlmanacMap{" +
                "sourceCategory=" + sourceCategory +
                ", mapRecords=" + mapRecords +
                '}';
    }
}
