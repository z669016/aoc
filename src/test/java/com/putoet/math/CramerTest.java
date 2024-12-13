package com.putoet.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CramerTest {
    @org.junit.jupiter.api.Test
    void solve() {
        final var a = new double[][]{
                {3, 2},
                {2, 3}
        };
        final var b = new double[]{7, 8};

        final var x = Cramer.solve(a, b);
        assertTrue(x.isPresent());
        assertEquals(2, x.get().length);
        assertEquals(1, x.get()[0]);
        assertEquals(2, x.get()[1]);
    }
}