package com.putoet.utils;

import com.diogonunes.jcolor.Attribute;

import java.util.function.Supplier;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Timer {
    public static <T> T run(Supplier<T> supplier) {
        final var start = System.nanoTime();
        final var value = supplier.get();
        final var end = System.nanoTime();
        System.out.println(colorize("Execution time: " + (end - start) / 1_000_000 + " ms", Attribute.GREEN_TEXT()));
        return value;
    }

    public static void run(Runnable runnable) {
        final var start = System.nanoTime();
        runnable.run();
        final var end = System.nanoTime();
        System.out.println(colorize("Execution time: " + (end - start) / 1_000_000 + " ms", Attribute.GREEN_TEXT()));
    }
}
