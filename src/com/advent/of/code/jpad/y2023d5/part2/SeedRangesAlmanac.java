package com.advent.of.code.jpad.y2023d5.part2;

import com.advent.of.code.jpad.utils.ParseableString;
import com.advent.of.code.jpad.y2023d5.generic.Almanac;

import java.util.ArrayList;
import java.util.List;

import static com.advent.of.code.jpad.y2023d5.generic.Category.SEED;

public class SeedRangesAlmanac extends Almanac {

    private final List<SeedRange> seedRanges; // Part 2

    private SeedRangesAlmanac(List<SeedRange> seedRanges, List<String> inputElements) {
        super(inputElements);
        this.seedRanges = seedRanges;
    }

    public static SeedRangesAlmanac of(String input) {
        List<String> inputElements = getInputElements(input);

        List<Long> seedInputNumbers = ParseableString.of(inputElements.get(0))
                .valueBetween("seeds: ", "")
                .valuesSeparatedBy(" ")
                .map(ParseableString::toLongValue).toList();

        List<SeedRange> seedRanges = new ArrayList<>();
        for (int i = 0; i < seedInputNumbers.size(); i+=2) {
            seedRanges.add(SeedRange.of(seedInputNumbers.get(i), seedInputNumbers.get(i+1)));
        }

        return new SeedRangesAlmanac(seedRanges, inputElements);
    }

    public List<Long> getSeedRangeMinLocations() {
        List<Long> seedRangesMinLocations = new ArrayList<>();
        for (SeedRange range : seedRanges) {
            Long minLocationInRange = getMinimumLocationRecursively(range);
            seedRangesMinLocations.add(minLocationInRange);
        }
        return seedRangesMinLocations;
    }

    private Long getMinimumLocationRecursively(SeedRange range) {
        long locationOfStartSeed = getLocationRecursively(range.getStart(), SEED);
        long locationOfEndSeed = getLocationRecursively(range.getEnd(), SEED);
        long locationRangeLength = locationOfEndSeed - locationOfStartSeed + 1;
        if (locationRangeLength != range.getLength()) {
            return Math.min(getMinimumLocationRecursively(range.getFirstHalf()), getMinimumLocationRecursively(range.getSecondHalf()));
        } else {
            return locationOfStartSeed;
        }
    }
}
