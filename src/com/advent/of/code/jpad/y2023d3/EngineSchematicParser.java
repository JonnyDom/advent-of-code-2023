package com.advent.of.code.jpad.y2023d3;

import java.util.ArrayList;
import java.util.List;

public final class EngineSchematicParser {

    public static SchematicLine parseLine(String line) {
        List<NumberInLine> numbers = new ArrayList<>();
        List<SymbolInLine> symbols = new ArrayList<>();

        int currentNumber = -1;
        for (int index = 0; index < line.length(); index++) {
            char currentChar = line.charAt(index);
            if (currentChar == '.') {
                if (currentNumber != -1) {
                    processNumberEnding(numbers, currentNumber, index);
                    currentNumber = -1;
                }
            } else if (Character.isDigit(currentChar)) {
                currentNumber = currentNumber == -1 ? Character.getNumericValue(currentChar) : computeCurrentNumber(currentNumber, currentChar);
                if (index == line.length() -1) { // process number at the end of the line
                    processNumberEnding(numbers, currentNumber, index);
                }
            } else {
                if (currentNumber != -1) {
                    processNumberEnding(numbers, currentNumber, index); // process number in the middle of the line
                    currentNumber = -1;
                }
                symbols.add(SymbolInLine.of(currentChar, index));
            }
        }
        return SchematicLine.with(numbers, symbols);
    }

    private static void processNumberEnding(List<NumberInLine> numbers, int numberBeingProcessed, int index) {
        numbers.add(NumberInLine.of(numberBeingProcessed, index - String.valueOf(numberBeingProcessed).length()));
    }

    private static int computeCurrentNumber(int currentNumber, char c) {
        return 10 * currentNumber + Character.getNumericValue(c);
    }
}
