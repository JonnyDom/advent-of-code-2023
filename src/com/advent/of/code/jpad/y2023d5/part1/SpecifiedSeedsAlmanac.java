package com.advent.of.code.jpad.y2023d5.part1;

import com.advent.of.code.jpad.utils.ParseableString;
import com.advent.of.code.jpad.y2023d5.generic.Almanac;

import java.util.List;

import static com.advent.of.code.jpad.y2023d5.generic.Category.SEED;

public class SpecifiedSeedsAlmanac extends Almanac {

    private List<Long> seeds; // Part 1

    private SpecifiedSeedsAlmanac(List<Long> seeds, List<String> inputElements) {
        super(inputElements);
        this.seeds = seeds;
    }

    public static SpecifiedSeedsAlmanac of(String input) {
        List<String> inputElements = getInputElements(input);
        List<Long> seedsInput = ParseableString.of(inputElements.get(0))
                .valueBetween("seeds: ", "")
                .valuesSeparatedBy(" ")
                .map(ParseableString::toLongValue).toList();
        return new SpecifiedSeedsAlmanac(seedsInput, inputElements);
    }

    public List<Long> getSeedLocations() {
        return seeds.stream().map(seed -> getLocationRecursively(seed, SEED)).toList();
    }
}
