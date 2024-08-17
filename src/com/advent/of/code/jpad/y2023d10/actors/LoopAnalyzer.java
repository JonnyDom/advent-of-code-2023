package com.advent.of.code.jpad.y2023d10.actors;

public class LoopAnalyzer {
    private final Sketch sketch;

    public LoopAnalyzer(Sketch sketch) {
        this.sketch = sketch;
    }

    public int computeFarthestDistanceFromAnimal() {
        // first step
        Direction currentDirection = sketch.computeStartingDirection();
        Position currentPosition = sketch.getAnimalPosition().nextPosition(currentDirection);
        int loopLength = 1;

        // process entire loop until we're back at the animal
        while (!sketch.getAnimalPosition().equals(currentPosition)) {
            Pipe currentPipe = Pipe.fromSymbol(sketch.getTileOn(currentPosition));
            currentDirection = currentPipe.getNextDirectionAfterComingFrom(currentDirection);
            currentPosition = currentPosition.nextPosition(currentDirection);
            loopLength++;
        }
        return loopLength / 2;
    }
}
