package com.advent.of.code.jpad.utils;

import java.util.*;

public class MathUtils {

    /**
     * Implemented with the greatest common divisor (GCD) of two numbers using Euclid's algorithm
     * @param numbers for which we want to know the least common multiple
     * @return the least common multiple
     */
    public static long computeLeastCommonMultiple(Set<Long> numbers){
        return numbers.stream().reduce(1L, MathUtils::lcmOfTwoNumbers);
    }

    // Helper method to calculate the LCM of two numbers
    private static long lcmOfTwoNumbers(long a, long b) {
        return Math.abs(a * b) / greatestCommonDivisor(a, b);
    }

    // Helper method to calculate the GCD of two numbers using Euclid's algorithm
    private static long greatestCommonDivisor(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
