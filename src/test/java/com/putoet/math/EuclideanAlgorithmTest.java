package com.putoet.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EuclideanAlgorithmTest {

    @Test
    void gcd() {
        assertEquals(6, EuclideanAlgorithm.gcd(12, 18));
    }

    @Test
    void lcm() {
        assertEquals(36, EuclideanAlgorithm.lcm(12, 18));
    }
}