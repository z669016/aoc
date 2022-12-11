package com.putoet.math;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FactorsTest {

    @Test
    void list() {
        assertEquals(List.of(1, 13), Factors.list(13));
        assertEquals(List.of(1, 3, 9), Factors.list(9));
        assertEquals(List.of(1, 2, 3, 5, 6, 10, 15, 30), Factors.list(30));
    }

    @Test
    void gcd() {
        assertEquals(6, Factors.gcd(24, 18));
        assertEquals(6L, Factors.gcd(24L, 18L));
    }

    @Test
    void lcm() {
        assertEquals(72, Factors.lcm(24, 18));
        assertEquals(72L, Factors.lcm(24L, 18L));
    }
}