package com.advent.of.code.jpad.y2023d10.actors.part1;

import com.advent.of.code.jpad.y2023d10.actors.shared.LoopAnalyzer;
import com.advent.of.code.jpad.y2023d10.actors.shared.Sketch;

public class LoopDistanceAnalyzer extends LoopAnalyzer {

    public LoopDistanceAnalyzer(Sketch sketch) {
        super(sketch);
    }

    @Override
    public int compute() {
        return computeLoop().size() / 2;
    }
}
