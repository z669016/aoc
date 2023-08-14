package com.putoet.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factors {
    public static List<Integer> list(int num) {
        final var factors = new ArrayList<Integer>();

        final int incrementer = num % 2 == 0 ? 1 : 2;
        for (int i = 1; i <= Math.sqrt(num); i += incrementer) {
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
