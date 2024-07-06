package com.putoet.utils;

import com.diogonunes.jcolor.Attribute;

import java.util.function.Supplier;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * A simple timer to measure the execution time of a given supplier or runnable.
 */
public class Timer {
    /**
     * Run the given supplier and measure the execution time. The runtime is measured in nanoseconds and printed in
     * milliseconds.
     * @param supplier The supplier to run
     * @return The result of the supplier (type T)
     */
    public static <T> T run(Supplier<T> supplier) {
        final var start = System.nanoTime();
        final var value = supplier.get();
        final var end = System.nanoTime();
        System.out.println(colorize("Execution time: " + (end - start) / 1_000_000 + " ms", Attribute.GREEN_TEXT()));
        return value;
    }

    /**
     * Run the given runnable and measure the execution time. The runtime is measured in nanoseconds and printed in
     * milliseconds.
     * @param runnable The runnable to run
     */
    public static void run(Runnable runnable) {
        final var start = System.nanoTime();
        runnable.run();
        final var end = System.nanoTime();
        System.out.println(colorize("Execution time: " + (end - start) / 1_000_000 + " ms", Attribute.GREEN_TEXT()));
    }
}
