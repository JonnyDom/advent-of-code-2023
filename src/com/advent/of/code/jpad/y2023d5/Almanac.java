package com.advent.of.code.jpad.y2023d5;

import com.advent.of.code.jpad.utils.ParseableString;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static com.advent.of.code.jpad.y2023d5.Category.SEED;
import static java.util.stream.Collectors.*;

public class Almanac {

    private final List<Long> seeds;
    private final Map<Category, AlmanacMap> almanacMaps;

    private Almanac(List<Long> seeds, Map<Category, AlmanacMap> almanacMaps) {
        this.seeds = seeds;
        this.almanacMaps = almanacMaps;
    }

    public static Almanac ofSpecifiedSeeds(String input) {
        return getAlmanac(input, UnaryOperator.identity());
    }

    private static Almanac getAlmanac(String input, UnaryOperator<List<Long>> seedsComputation) {
        List<String> inputElements = ParseableString.of(input)
                .valuesSeparatedBy("\r\n\r\n")
                .map(ParseableString::toStringValue).toList();

        List<Long> seedsInput = ParseableString.of(inputElements.get(0))
                .valueBetween("seeds: ", "")
                .valuesSeparatedBy(" ")
                .map(ParseableString::toLongValue).toList();
        List<Long> seeds = seedsComputation.apply(seedsInput);

        Map<Category, AlmanacMap> almanacMaps = inputElements.stream()
                .skip(1) // skip seeds block
                .map(AlmanacMap::fromMapBlock)
                .collect(toMap(AlmanacMap::getSourceCategory, Function.identity()));
        almanacMaps.values().forEach(System.out::println);
        return new Almanac(seeds, almanacMaps);
    }

    public List<Long> getSeedLocations() {
        return seeds.stream().map(seed -> getLocationRecursively(seed, SEED)).toList();
    }

    private long getLocationRecursively(long sourceValue, Category category) {
        return category.getMappedCategory() == null
                ? sourceValue
                : getLocationRecursively(almanacMaps.get(category).getDestinationFromSource(sourceValue), category.getMappedCategory());
    }
}
