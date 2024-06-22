package com.advent.of.code.jpad.y2023d8;

import java.util.List;
import java.util.stream.Stream;

public class NodeMap {
    private final List<Instruction> instructions;
    private final NodeNetwork nodeNetwork;

    public static NodeMap parseInput(Stream<String> lines) {
        List<String> input = lines.toList();
        NodeNetwork network = NodeNetwork.parseInput(input.subList(2, input.size()));
        return new NodeMap(input.get(0).chars().mapToObj(Instruction::fromLabel).toList(), network);
    }

    private NodeMap(List<Instruction> instructions, NodeNetwork nodeNetwork) {
        this.instructions = instructions;
        this.nodeNetwork = nodeNetwork;
    }

    public int countStepsNeededToReachEndNode() {
        return nodeNetwork.processInstructionsUntilEndNode(instructions);
    }
}
