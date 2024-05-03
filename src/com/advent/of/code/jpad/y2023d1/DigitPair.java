package com.advent.of.code.jpad.y2023d1;

public class DigitPair {

    private final String leftMost;
    private final String rightMost;

    private DigitPair(String leftMost, String rightMost) {
        this.leftMost = leftMost;
        this.rightMost = rightMost;
    }

    public static DigitPair of(String leftMost, String rightMost) {
        return new DigitPair(leftMost, rightMost);
    }

    public String getLeftMost() {
        return leftMost;
    }

    public String getRightMost() {
        return rightMost;
    }
}
