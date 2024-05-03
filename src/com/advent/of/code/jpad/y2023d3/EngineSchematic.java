package com.advent.of.code.jpad.y2023d3;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EngineSchematic {
    Map<Integer, List<NumberInLine>> numbersByLine = new HashMap<>();
    Map<Integer, List<SymbolInLine>> symbolsByLine = new HashMap<>();

    public EngineSchematic(List<SchematicLine> schematicLines) {
        for (int i = 0; i < schematicLines.size(); i++) {
            numbersByLine.put(i, schematicLines.get(i).getNumbers());
            symbolsByLine.put(i, schematicLines.get(i).getSymbols());
        }
    }

    public List<Integer> processToReturnEnginePartNumbers() {
        symbolsByLine.forEach((lineIndex, symbolsInLine) -> symbolsInLine.forEach(triggerNumbersInAdjacentLines(lineIndex)));
        return numbersByLine.values().stream()
                .flatMap(Collection::stream)
                .filter(NumberInLine::isAdjacentToAnySymbol)
                .map(NumberInLine::getNumber)
                .collect(Collectors.toList());
    }

    private Consumer<SymbolInLine> triggerNumbersInAdjacentLines(Integer lineIndex) {
        return symbol -> { // trigger the numbers on the same line or adjacent lines
            Stream.of(lineIndex - 1, lineIndex, lineIndex + 1)
                    .map(numbersByLine::get)
                    .filter(Objects::nonNull)
                    .flatMap(Collection::stream)
                    .forEach(checkForAdjacency(symbol));
        };
    }

    private static Consumer<NumberInLine> checkForAdjacency(SymbolInLine symbol) {
        return numberInLine -> numberInLine.checkForAdjacency(symbol);
    }

    /**
     * 2nd part of the puzzle
     *
     * @return list of gear ratios
     */
    public List<Integer> processToReturnGearRatios() {
        List<Integer> gearRatios = new ArrayList<>();
        symbolsByLine.forEach((lineIndex, symbolsInLine) -> symbolsInLine
                .stream()
                .filter(SymbolInLine::isGearSymbol)
                .map(toGear(lineIndex))
                .filter(Gear::hasTwoParts)
                .map(Gear::getRatio)
                .forEach(gearRatios::add)
        );
        return gearRatios;
    }

    private Function<SymbolInLine, Gear> toGear(Integer lineIndex) {
        return symbol -> {
            List<Integer> partNumbersInGear = Stream.of(lineIndex - 1, lineIndex, lineIndex + 1)
                    .map(numbersByLine::get)
                    .filter(Objects::nonNull)
                    .flatMap(Collection::stream)
                    .filter(isAdjacentTo(symbol))
                    .map(NumberInLine::getNumber)
                    .toList();
            return partNumbersInGear.size() == 2
                    ? Gear.of(partNumbersInGear.get(0), partNumbersInGear.get(1))
                    : Gear.empty();
        };
    }

    private Predicate<NumberInLine> isAdjacentTo(SymbolInLine symbol) {
        return numberInLine -> numberInLine.isAdjacentTo(symbol.getPosition());
    }
}
