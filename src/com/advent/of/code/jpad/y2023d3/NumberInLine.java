package com.advent.of.code.jpad.y2023d3;

public class NumberInLine {
    private int number;
    private final int startPosition;
    private final int endPosition;
    private boolean isAdjacentToAnySymbol = false;

    private NumberInLine(int number, int startPosition) {
        this.number = number;
        this.startPosition = startPosition;
        this.endPosition = startPosition + String.valueOf(number).length();
    }

    public static NumberInLine of(int number, int startPosition) {
        return new NumberInLine(number, startPosition);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Number:(" + number +
                ")@" + startPosition;
    }

    public void checkForAdjacency(SymbolInLine symbolInLine) {
        if (isAdjacentToAnySymbol) return;
        isAdjacentToAnySymbol = isAdjacentTo(symbolInLine.getPosition());
    }

    public boolean isAdjacentTo(int symbolPosition) {
        return startPosition - 1 <= symbolPosition && endPosition >= symbolPosition;
    }

    public boolean isAdjacentToAnySymbol() {
        return isAdjacentToAnySymbol;
    }
}
