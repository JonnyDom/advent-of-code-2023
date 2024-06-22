package com.advent.of.code.jpad.y2023d7.generic;

public interface  Rank {
    boolean isStrongerThan(Rank other);

    String getName();
}
