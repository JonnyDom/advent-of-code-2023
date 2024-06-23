package com.advent.of.code.jpad.y2023d8.generic;

import com.advent.of.code.jpad.utils.ParseableString;

public class Node {
    private final String label;
    private final String fullLine;
    private Node leftNode;
    private Node rightNode;

    public static Node parseLabel(String line) {
        return new Node(line.substring(0, 3), line);
    }

    private Node(String label, String fullLine) {
        this.label = label;
        this.fullLine = fullLine;
    }

    public String getLabel() {
        return label;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public String getLeftNodeLabel() {
        return ParseableString.of(fullLine).valueBetween("= (", ",").toStringValue();
    }

    public String getRightNodeLabel() {
        return ParseableString.of(fullLine).valueBetween(", ", ")").toStringValue();
    }

    public Node getNextNodeBasedOnInstruction(Instruction instruction) {
        return switch (instruction) {
            case LEFT -> leftNode;
            case RIGHT -> rightNode;
        };
    }
}
