package com.advent.of.code.jpad.y2023d5.generic;

import com.advent.of.code.jpad.utils.ParseableString;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public abstract class Almanac {

    protected Map<Category, AlmanacMap> almanacMaps;

    protected Almanac() {
    }

    protected Almanac(List<String> inputElements) {
        this.almanacMaps = getAlmanacMaps(inputElements);
    }

    protected static List<String> getInputElements(String input) {
        return ParseableString.of(input)
                .valuesSeparatedBy("\r\n\r\n")
                .map(ParseableString::toStringValue).toList();
    }

    private Map<Category, AlmanacMap> getAlmanacMaps(List<String> inputElements) {
        return inputElements.stream()
                .skip(1) // skip seeds block
                .map(AlmanacMap::fromMapBlock)
                .collect(toMap(AlmanacMap::getSourceCategory, Function.identity()));
    }

    protected long getLocationRecursively(long sourceValue, Category category) {
        return category.getMappedCategory() == null
                ? sourceValue
                : getLocationRecursively(almanacMaps.get(category).getDestinationFromSource(sourceValue), category.getMappedCategory());
    }
}
