package com.advent.of.code.jpad.y2023d8.part2;


import com.advent.of.code.jpad.utils.MathUtils;
import com.advent.of.code.jpad.y2023d8.generic.Instruction;
import com.advent.of.code.jpad.y2023d8.generic.Node;
import com.advent.of.code.jpad.y2023d8.generic.NodeNetwork;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NodeNetwork2 implements NodeNetwork {
    private static final Predicate<String> BEGIN_NODE_LABEL_MATCHER = label -> label.endsWith("A");
    private final Map<String, Node> nodesByLabel;

    public NodeNetwork2(Map<String, Node> nodesByLabel) {
        this.nodesByLabel = nodesByLabel;
    }

    /**
     * Initialize a thread for each node.
     * Each thread gathers the minimum steps to get to a 'Z' given their starting node.
     * Then, and because there's a pattern in each node for the steps to get to 'Z',
     * we just get the least common multiple of all the obtained minimums.
     * @param instructions the instructions to follow
     * @return the number of steps that it takes before we're only on nodes that end with Z
     */
    @Override
    public long processInstructionsUntilEndNode(List<Instruction> instructions) {
        Set<String> startingNodes = nodesByLabel.keySet().stream()
                .filter(BEGIN_NODE_LABEL_MATCHER)
                .collect(Collectors.toSet());

        if (startingNodes.size() > 20) {
            throw new IllegalArgumentException(String.format("%s is more than the allowed number of starting nodes to process", startingNodes.size()));
        }

        ExecutorService executor = Executors.newFixedThreadPool(startingNodes.size());
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        List<Future<Integer>> futures = new ArrayList<>();
        startingNodes.forEach(startingNode -> futures.add(completionService.submit(new NodeAnalyzerTask(startingNode, nodesByLabel, instructions))));

        Set<Long> minimumStepsToGetToZNodeForAllStartingNodes = new HashSet<>();
        for (Future<Integer> future : futures) {
            try {
                minimumStepsToGetToZNodeForAllStartingNodes.add(future.get().longValue());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Error getting the steps solution for one of the starting nodes");
            }
        }
        executor.shutdownNow();

        return MathUtils.computeLeastCommonMultiple(minimumStepsToGetToZNodeForAllStartingNodes);
    }
}
