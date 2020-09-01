package com.putoet.resources;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSV {
    public static String DEFAULT_REGEX = ",";

    public static List<List<String>> list(String resourceName) {
        return list(resourceName, DEFAULT_REGEX);
    }

    public static List<List<String>> list(String resourceName, String regex) {
        return stream(resourceName, regex)
                .map(stream -> stream.collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static List<String> flatList(String resourceName) {
        return flatList(resourceName, DEFAULT_REGEX);
    }

    public static List<String> flatList(String resourceName, String regex) {
        return stream(resourceName, regex)
                .flatMap(stream -> stream)
                .collect(Collectors.toList());
    }

    public static <T> List<List<T>> list(String resourceName, Function<String,T> mapper) {
        return list(resourceName, DEFAULT_REGEX, mapper);
    }

    public static <T> List<List<T>> list(String resourceName, String regex, Function<String,T> mapper) {
        return stream(resourceName, regex)
                .map(stream -> stream.map(mapper).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static <T> List<T> flatList(String resourceName, Function<String,T> mapper) {
        return flatList(resourceName, DEFAULT_REGEX, mapper);
    }

    public static <T> List<T> flatList(String resourceName, String regex, Function<String,T> mapper) {
        return stream(resourceName, regex)
                .flatMap(stream -> stream.map(mapper))
                .collect(Collectors.toList());
    }

    private static Stream<Stream<String>> stream(String resourceName, String regex) {
        return ResourceLines.stream(resourceName)
                .map(line -> line.split(regex))
                .map(Arrays::stream);
    }
}
