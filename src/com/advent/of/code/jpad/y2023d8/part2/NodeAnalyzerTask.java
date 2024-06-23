package com.advent.of.code.jpad.y2023d8.part2;

import com.advent.of.code.jpad.y2023d8.generic.Instruction;
import com.advent.of.code.jpad.y2023d8.generic.Node;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class NodeAnalyzerTask implements Callable<Integer> {
    private static final Predicate<String> END_NODE_LABEL_MATCHER = label -> label.endsWith("Z");
    private static final Long MAX_STEPS = 100_000_000L;

    private final String startingNode;
    private final Map<String, Node> nodesByLabel;
    private final List<Instruction> instructions;

    public NodeAnalyzerTask(String startingNode,
                            Map<String, Node> nodesByLabel,
                            List<Instruction> instructions) {
        this.startingNode = startingNode;
        this.nodesByLabel = nodesByLabel;
        this.instructions = instructions;
    }

    @Override
    public Integer call() {
        Node currentNode = nodesByLabel.get(startingNode);
        int steps = 0;
        int instructionIndex = 0;
        while (steps < MAX_STEPS) {

            Instruction currentInstruction = instructions.get(instructionIndex++);
            if (instructionIndex >= instructions.size()) {
                instructionIndex = 0;
            }
            currentNode = currentNode.getNextNodeBasedOnInstruction(currentInstruction);
            steps++;
            if (END_NODE_LABEL_MATCHER.test(currentNode.getLabel())) {
                return steps;
            }
        }
        throw new RuntimeException("Found no steps solution for starting node " + startingNode);
    }
}
