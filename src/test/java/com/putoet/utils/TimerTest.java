package com.putoet.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimerTest {

    @Test
    void timerRunnable() {
        Timer.run(() -> System.out.println("Hello world!"));
    }

    @Test
    void timerSupplier() {
        var i = Timer.run(() -> {
            final var year = 2023;
            System.out.println("Hello world of " + year + "!");
            return year;
        });
        assertEquals(2023, i);
    }
}