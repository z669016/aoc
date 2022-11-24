package com.putoet.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JosephusTest {
    @Test
    void winner() {
        assertEquals(19, Josephus.winner(41));
    }
}