package com.advent.of.code.jpad.y2023d8.part1;


import com.advent.of.code.jpad.y2023d8.generic.Instruction;
import com.advent.of.code.jpad.y2023d8.generic.Node;
import com.advent.of.code.jpad.y2023d8.generic.NodeNetwork;

import java.util.List;
import java.util.Map;

public class NodeNetwork1 implements NodeNetwork {
    private static final String BEGIN_NODE_LABEL = "AAA";
    private static final String END_NODE_LABEL = "ZZZ";
    private final Map<String, Node> nodesByLabel;

    public NodeNetwork1(Map<String, Node> nodesByLabel) {
        this.nodesByLabel = nodesByLabel;
    }

    @Override
    public long processInstructionsUntilEndNode(List<Instruction> instructions) {
        Node currentNode = nodesByLabel.get(BEGIN_NODE_LABEL);
        int steps = 0;
        while (!currentNode.getLabel().equals(END_NODE_LABEL)) {
            Instruction currentInstruction = instructions.get(steps % instructions.size());
            currentNode = currentNode.getNextNodeBasedOnInstruction(currentInstruction);
            steps++;
        }
        return steps;
    }
}
