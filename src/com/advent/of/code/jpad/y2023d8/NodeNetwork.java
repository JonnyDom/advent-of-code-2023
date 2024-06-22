package com.advent.of.code.jpad.y2023d8;


import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class NodeNetwork {
    private static final String BEGIN_NODE_LABEL = "AAA";
    private static final String END_NODE_LABEL = "ZZZ";
    private final Map<String, Node> nodesByLabel;

    public static NodeNetwork parseInput(List<String> nodesLines) {
        Map<String, Node> nodesMap = initMap(nodesLines);
        nodesMap.values().forEach(node -> {
            node.setLeftNode(nodesMap.get(node.getLeftNodeLabel()));
            node.setRightNode(nodesMap.get(node.getRightNodeLabel()));
        });
        return new NodeNetwork(nodesMap);
    }

    private static Map<String, Node> initMap(List<String> nodesLines) {
        return nodesLines.stream().map(Node::parseLabel).collect(toMap(Node::getLabel, Function.identity()));
    }

    private NodeNetwork(Map<String, Node> nodesByLabel) {
        this.nodesByLabel = nodesByLabel;
    }

    public int processInstructionsUntilEndNode(List<Instruction> instructions) {
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
