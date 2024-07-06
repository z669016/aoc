package com.putoet.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple utility class to calculate the factors of a given number.
 */
public class Factors {
    /**
     * Calculate the factors of the given number.
     * @param num The number to calculate the factors for
     * @return A sorted list of factors
     */
    public static List<Integer> list(int num) {
        final var factors = new ArrayList<Integer>();

        final var incrementer = num % 2 == 0 ? 1 : 2;
        for (var i = 1; i <= Math.sqrt(num); i += incrementer) {
            if (num % i == 0) {
                factors.add(i);
                if (i != num / i) {
                    factors.add(num / i);
                }
            }
        }

        Collections.sort(factors);
        return factors;
    }
}
