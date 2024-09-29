package com.advent.of.code.jpad.y2023d10.actors.shared;

import java.util.LinkedList;
import java.util.List;

public abstract class LoopAnalyzer {
    protected final Sketch sketch;

    public LoopAnalyzer(Sketch sketch) {
        this.sketch = sketch;
    }

    public abstract int compute();

    protected LinkedList<LoopTile> computeLoop() {
        // first step out of the animal tile
        Direction currentDirection = sketch.computeStartingDirection();
        LinkedList<LoopTile> loop = new LinkedList<>(List.of(new LoopTile(sketch.getAnimalPosition(), null, currentDirection)));
        Position currentPosition = sketch.getAnimalPosition().nextPosition(currentDirection);

        // process entire loop until we're back at the animal
        while (!sketch.getAnimalPosition().equals(currentPosition)) {
            Pipe currentPipe = Pipe.fromSymbol(sketch.getTileOn(currentPosition));
            currentDirection = currentPipe.getNextDirectionAfterComingFrom(currentDirection);
            loop.add(new LoopTile(currentPosition, currentPipe, currentDirection));
            currentPosition = currentPosition.nextPosition(currentDirection);
        }

        replaceAnimalWithProperPipe(loop);

        return loop;
    }

    private void replaceAnimalWithProperPipe(LinkedList<LoopTile> loop) {
        loop.set(0, new LoopTile(
                loop.getFirst().position(),
                Pipe.fromPreviousAndFollowingPipes(loop.getLast().goingTo(), loop.getFirst().goingTo()),
                loop.getFirst().goingTo())
        );
    }

}
