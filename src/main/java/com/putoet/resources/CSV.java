package com.putoet.resources;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSV {
    public static List<List<String>> list(String resourceName) {
        return stream(resourceName)
                .map(stream -> stream.collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static List<String> flatList(String resourceName) {
        return stream(resourceName)
                .flatMap(stream -> stream)
                .collect(Collectors.toList());
    }

    public static <T> List<List<T>> list(String resourceName, Function<String,T> mapper) {
        return stream(resourceName)
                .map(stream -> stream.map(mapper).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static <T> List<T> flatList(String resourceName, Function<String,T> mapper) {
        return stream(resourceName)
                .flatMap(stream -> stream.map(mapper))
                .collect(Collectors.toList());
    }

    private static Stream<Stream<String>> stream(String resourceName) {
        return ResourceLines.stream(resourceName)
                .map(line -> line.split(","))
                .map(Arrays::stream);
    }
}
