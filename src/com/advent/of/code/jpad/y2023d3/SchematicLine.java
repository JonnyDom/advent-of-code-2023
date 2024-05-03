package com.advent.of.code.jpad.y2023d3;

import java.util.List;

public class SchematicLine {
    private final List<NumberInLine> numbers;
    private final List<SymbolInLine> symbols;

    private SchematicLine(List<NumberInLine> numbers, List<SymbolInLine> symbols) {
        this.numbers = numbers;
        this.symbols = symbols;
    }

    public static SchematicLine with(List<NumberInLine> numbers, List<SymbolInLine> symbols) {
        return new SchematicLine(numbers, symbols);
    }

    public List<NumberInLine> getNumbers() {
        return numbers;
    }

    public List<SymbolInLine> getSymbols() {
        return symbols;
    }

    @Override
    public String toString() {
        return "SchematicInLine{" +
                "numbers=" + numbers +
                ", symbols=" + symbols +
                '}';
    }
}
