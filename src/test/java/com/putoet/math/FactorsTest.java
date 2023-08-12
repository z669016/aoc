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
}