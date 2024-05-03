package com.advent.of.code.jpad.y2023d3;

public class SymbolInLine {
    private final char symbol;
    private final int position;

    private SymbolInLine(char symbol, int position) {
        this.symbol = symbol;
        this.position = position;
    }

    public static SymbolInLine of(char symbol, int position) {
        return new SymbolInLine(symbol, position);
    }

    @Override
    public String toString() {
        return "Symbol:(" + symbol + ")@" + position ;
    }

    public int getPosition() {
        return position;
    }

    public Character getSymbol() {
        return symbol;
    }

    public boolean isGearSymbol() {
        return symbol == '*';
    }
}
