package com.advent.of.code.jpad.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParseableString {

    private final String input;

    private ParseableString(String input) {
        this.input = input;
    }

    public static ParseableString of(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("String to be parsed must not be blank");
        }
        return new ParseableString(input);
    }

    public ParseableString valueBetween(String firstSubstring, String secondSubstring) {
        int startIndex = firstSubstring.isBlank() ? 0 : input.indexOf(firstSubstring) + firstSubstring.length();
        int endIndex = secondSubstring.isBlank() ? input.length() : input.indexOf(secondSubstring);
        return new ParseableString(input.substring(startIndex, endIndex).trim());
    }

    public ParseableString withContentReplaced(String contentReplaced, String replacingContent) {
        return new ParseableString(input.replaceAll(" ", ""));
    }

    public Stream<ParseableString> valuesSeparatedBy(String separator) {
        return Arrays.stream(input.split(separator)).map(String::trim).map(ParseableString::new);
    }

    public Stream<ParseableString> valuesSeparatedByBlanks() {
        if (input.isBlank()) {
            return Stream.of();
        }

        List<String> nonBlanks = new ArrayList<>();
        String parsedInput = input;
        while (!parsedInput.isBlank()) {
            int blankIndex = parsedInput.indexOf(' ');
            int newEndIndex = blankIndex != -1 ? blankIndex : parsedInput.length();
            String newElement = parsedInput.substring(0, newEndIndex).trim();
            if (!newElement.isBlank()) {
                nonBlanks.add(newElement);
            }
            parsedInput = parsedInput.substring(newEndIndex).trim();
        }

        return nonBlanks.stream().map(ParseableString::new);
    }

    public String toStringValue(){
        return input;
    }

    public int toIntValue(){
        return Integer.parseInt(input);
    }

    public long toLongValue(){
        return Long.parseLong(input);
    }

    public boolean nonBlank() {
        return !input.isBlank();
    }

    @Override
    public String toString() {
        return input;
    }
}
