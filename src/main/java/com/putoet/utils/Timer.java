package com.putoet.utils;

import java.util.function.Supplier;

public class Timer {
    public static <T> T run(Supplier<T> supplier) {
        final long start = System.nanoTime();
        final var value = supplier.get();
        final long end = System.nanoTime();
        System.out.println("Execution time: " + (end - start) / 1_000_000 + " ms");
        return value;
    }

    public static void run(Runnable runnable) {
        final long start = System.nanoTime();
        runnable.run();
        final long end = System.nanoTime();
        System.out.println("Execution time: " + (end - start) / 1_000_000 + " ms");
    }
}
