package com.advent.of.code.jpad.y2023d9.actors;

import java.util.LinkedList;
import java.util.List;
import java.util.function.*;

public class AnalysisProcessor {

    public static final Predicate<Integer> BEING_ZERO = i -> i == 0;

    public enum ExtrapolationType {
        PAST(LinkedList::getFirst, LinkedList::addFirst, (a,b) -> a - b),
        FUTURE(LinkedList::getLast, LinkedList::add, Integer::sum);

        private final Function<LinkedList<Integer>, Integer> retrieveElement;
        private final BiConsumer<LinkedList<Integer>, Integer> addToSequence;
        private final IntBinaryOperator operation;

        ExtrapolationType(Function<LinkedList<Integer>, Integer> retrieveElement, BiConsumer<LinkedList<Integer>, Integer> addToSequence, IntBinaryOperator operation) {
            this.retrieveElement = retrieveElement;
            this.operation = operation;
            this.addToSequence = addToSequence;
        }
    }

    public static Integer extrapolateFromHistory(LinkedList<Integer> pastReadings, ExtrapolationType type) {
        pastReadings.getLast();
        LinkedList<LinkedList<Integer>> fullDifferenceAnalysis = new LinkedList<>(List.of(pastReadings));

        while (!fullDifferenceAnalysis.getLast().stream().allMatch(BEING_ZERO)) {
            fullDifferenceAnalysis.add(getDifferenceAnalysisSequenceOf(fullDifferenceAnalysis.getLast()));
        }
        fullDifferenceAnalysis.getLast().add(0); // add extra zero to start computing new values up the sequence chain

        for (int i = fullDifferenceAnalysis.size() - 2; i >= 0; i--) {
            LinkedList<Integer> sequenceInAnalysis = fullDifferenceAnalysis.get(i);
            LinkedList<Integer> sequenceBelow = fullDifferenceAnalysis.get(i+1);
            type.addToSequence.accept(sequenceInAnalysis, predictNewElement(sequenceInAnalysis, sequenceBelow, type));
        }

        return type.retrieveElement.apply(fullDifferenceAnalysis.getFirst());
    }

    private static int predictNewElement(LinkedList<Integer> sequenceInAnalysis, LinkedList<Integer> sequenceBelow, ExtrapolationType type) {
        return type.operation.applyAsInt(
                type.retrieveElement.apply(sequenceInAnalysis),
                type.retrieveElement.apply(sequenceBelow)
        );
    }

    private static LinkedList<Integer> getDifferenceAnalysisSequenceOf(LinkedList<Integer> sequenceToAnalyze) {
        LinkedList<Integer> differenceAnalysis = new LinkedList<>();
        for (int i = 0; i < sequenceToAnalyze.size() - 1; i++) {
            differenceAnalysis.add(sequenceToAnalyze.get(i+1) - sequenceToAnalyze.get(i));
        }
        return differenceAnalysis;
    }

}
