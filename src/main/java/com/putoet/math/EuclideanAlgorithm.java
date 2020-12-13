package com.putoet.math;

import java.util.List;

public class EuclideanAlgorithm {
    public static long gcd(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return number1 + number2;
        } else {
            long absNumber1 = Math.abs(number1);
            long absNumber2 = Math.abs(number2);
            long biggerValue = Math.max(absNumber1, absNumber2);
            long smallerValue = Math.min(absNumber1, absNumber2);
            return gcd(biggerValue % smallerValue, smallerValue);
        }
    }

    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0)
            return 0;
        else {
            long gcd = gcd(number1, number2);
            return Math.abs(number1 * number2) / gcd;
        }
    }

    public static long gcd(List<Long> numbers) {
        return numbers.stream().reduce(0L, EuclideanAlgorithm::gcd);
    }

    public static long lcm(List<Long> numbers) {
        return numbers.stream().reduce(1L, (x, y) -> x * (y / gcd(x, y)));
    }
}
