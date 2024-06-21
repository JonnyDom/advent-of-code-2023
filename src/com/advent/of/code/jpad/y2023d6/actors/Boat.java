package com.advent.of.code.jpad.y2023d6.actors;

public class Boat {
    private final long boatSpeed; //millimeters per millisecond
    private long position = 0; //millimeters

    private Boat(long boatSpeed) {
        this.boatSpeed = boatSpeed;
    }

    public static Boat chargedWith(long milliseconds) {
        return new Boat(milliseconds);
    }

    public Boat moveForDuration(long milliSeconds) {
        position = boatSpeed * milliSeconds;
        return this;
    }

    public long getPosition() {
        return position;
    }
}
