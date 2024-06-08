package com.advent.of.code.jpad.y2023d5.part2;

public class SeedRange {
    private final long start;
    private final long length;
    private final long end;

    private SeedRange(long start, long length) {
        this.start = start;
        this.length = length;
        this.end = start + length - 1;
    }

    public static SeedRange of(final long start, final long length) {
        return new SeedRange(start, length);
    }

    @Override
    public String toString() {
        return "SeedRange{" + start + "-" + length + "}";
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public long getLength() {
        return length;
    }

    public SeedRange getFirstHalf() {
        return SeedRange.of(start, length / 2);
    }

    public SeedRange getSecondHalf() {
        return SeedRange.of( start + length / 2, length / 2);
    }
}
