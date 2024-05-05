package com.advent.of.code.jpad.utils;

import java.util.Arrays;
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

    public Stream<ParseableString> valuesSeparatedBy(String separator) {
        return Arrays.stream(input.split(separator)).map(String::trim).map(ParseableString::new);
    }

    public String toStringValue(){
        return input;
    }

    public boolean nonBlank() {
        return !input.isBlank();
    }
}
