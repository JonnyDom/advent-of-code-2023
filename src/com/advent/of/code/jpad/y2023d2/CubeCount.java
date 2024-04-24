package com.advent.of.code.jpad.y2023d2;

public class CubeCount {
    private Color color;
    private int amount;

    private CubeCount(Color color, int amount) {
        this.color = color;
        this.amount = amount;
    }

    public static CubeCount of(Color color, int amount) {
        return new CubeCount(color, amount);
    }

    public static CubeCount fromCubeLog(String log) {
        int amount = Integer.parseInt(log.substring(0, log.indexOf(' ')));
        Color color = Color.fromString(log.substring(2).trim());
        return new CubeCount(color, amount);
    }

    public Color getColor() {
        return color;
    }

    public int getAmount() {
        return amount;
    }
}
