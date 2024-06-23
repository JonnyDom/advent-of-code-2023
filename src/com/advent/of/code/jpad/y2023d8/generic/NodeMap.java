package com.advent.of.code.jpad.y2023d8.generic;

import java.util.List;
import java.util.stream.Stream;

public class NodeMap {
    private final List<Instruction> instructions;
    private final NodeNetwork nodeNetwork;

    public static NodeMap ofInput(Stream<String> lines, NodeNetworkFactory.Part part) {
        List<String> input = lines.toList();
        NodeNetwork network = NodeNetworkFactory.initializeNetwork(input.subList(2, input.size()), part);
        return new NodeMap(input.get(0).chars().mapToObj(Instruction::fromLabel).toList(), network);
    }

    private NodeMap(List<Instruction> instructions, NodeNetwork nodeNetwork) {
        this.instructions = instructions;
        this.nodeNetwork = nodeNetwork;
    }

    public long countStepsNeededToReachEndNode() {
        return nodeNetwork.processInstructionsUntilEndNode(instructions);
    }
}
