package com.putoet.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factors {
    public static List<Integer> list(int num) {
        final List<Integer> factors = new ArrayList<>();

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

    public static long gcd(final long first, final long second) {
        if (first == 0 || second == 0) {
            return first + second;
        } else {
            return gcd(Math.max(first, second) % Math.min(first, second), Math.min(first, second));
        }
    }

    public static int gcd(final int first, final int second) {
        if (first == 0 || second == 0) {
            return first + second;
        } else {
            return gcd(Math.max(first, second) % Math.min(first, second), Math.min(first, second));
        }
    }

    public static int lcm(final int first, final int second) {
        return first * second / gcd(first, second);
    }

    public static long lcm(final long first, final long second) {
        return first * second / gcd(first, second);
    }
}
