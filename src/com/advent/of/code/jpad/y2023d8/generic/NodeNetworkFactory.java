package com.advent.of.code.jpad.y2023d8.generic;

import com.advent.of.code.jpad.y2023d8.part1.NodeNetwork1;
import com.advent.of.code.jpad.y2023d8.part2.NodeNetwork2;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public final class NodeNetworkFactory {

    public enum Part {
        ONE(NodeNetwork1::new),
        TWO(NodeNetwork2::new);

        private final Function<Map<String, Node>, NodeNetwork> initializer;

        Part(Function<Map<String, Node>, NodeNetwork> initializer) {
            this.initializer = initializer;
        }

        public NodeNetwork apply(Map<String, Node> nodesMap) {
            return initializer.apply(nodesMap);
        }
    }

    private NodeNetworkFactory() {}

    public static NodeNetwork initializeNetwork(List<String> nodesLines, Part part) {
        return part.apply(parseInput(nodesLines));
    }

    private static Map<String, Node> parseInput(List<String> nodesLines) {
        Map<String, Node> nodesMap = initMap(nodesLines);
        nodesMap.values().forEach(node -> {
            node.setLeftNode(nodesMap.get(node.getLeftNodeLabel()));
            node.setRightNode(nodesMap.get(node.getRightNodeLabel()));
        });
        return nodesMap;
    }

    private static Map<String, Node> initMap(List<String> nodesLines) {
        return nodesLines.stream().map(Node::parseLabel).collect(toMap(Node::getLabel, Function.identity()));
    }

}
