package com.advent.of.code.jpad.y2023d3;

public class Gear {
    private Integer partNumber1;
    private Integer partNumber2;

    private Gear(int partNumber1, int partNumber2) {
        this.partNumber1 = partNumber1;
        this.partNumber2 = partNumber2;
    }

    private Gear() {
    }

    public static Gear of(int partNumber1, int partNumber2) {
        return new Gear(partNumber1, partNumber2);
    }

    public static Gear empty(){
        return new Gear();
    }

    public int getRatio(){
        return partNumber1 * partNumber2;
    }

    public boolean hasTwoParts() {
        return partNumber1 != null && partNumber2 != null;
    }
}
