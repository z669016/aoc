package com.putoet.resources;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CSV provides a simple way to read CSV resources from the classpath and convert them to a List of List of Strings or
 * a List of Strings. The resource name should start with a '/' and is relative to the classpath.
 */
public class CSV {
    /**
     * The default regex to split the CSV is ","
     */
    public static final String DEFAULT_REGEX = ",";

    /**
     * Return a List of List of Strings for the given resource name. The CSV is split on the default regex.
     * @param resourceName The name of the resource
     * @return A List of List of Strings for the given resource name
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     */
    public static List<List<String>> list(String resourceName) {
        return list(resourceName, DEFAULT_REGEX);
    }

    /**
     * Return a List of List of Strings for the given resource name. The CSV is split on the given regex
     * @param resourceName The name of the resource
     * @param regex The regex to split the CSV
     * @return A List of List of Strings for the given resource name
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     */
    public static List<List<String>> list(String resourceName, String regex) {
        return stream(resourceName, regex)
                .map(stream -> stream.collect(Collectors.toList()))
                .toList();
    }

    /**
     * Return a List of Strings for the given resource name. The CSV is split on the default regex
     * @param resourceName The name of the resource
     * @return A List of Strings for the given resource name
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     */
    public static List<String> flatList(String resourceName) {
        return flatList(resourceName, DEFAULT_REGEX);
    }

    /**
     * Return a List of Strings for the given resource name. The CSV is split on the given regex
     * @param resourceName The name of the resource
     * @param regex The regex to split the CSV
     * @return A List of Strings for the given resource name
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     */
    public static List<String> flatList(String resourceName, String regex) {
        return stream(resourceName, regex)
                .flatMap(stream -> stream)
                .toList();
    }

    /**
     * Return a List of List of objects type T for the given resource name. The CSV is split on the default regex
     * @param resourceName The name of the resource
     * @param mapper The function to map a String to an object of type T
     * @return A List of List of objects of type T for the given resource name
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     */
    public static <T> List<List<T>> list(String resourceName, Function<String,T> mapper) {
        return list(resourceName, DEFAULT_REGEX, mapper);
    }

    /**
     * Return a List of List of objects type T for the given resource name. The CSV is split on the given regex
     * @param resourceName The name of the resource
     * @param regex The regex to split the CSV
     * @param mapper The function to map a String to an object of type T
     * @return A List of List of objects of type T for the given resource name
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     */
    public static <T> List<List<T>> list(String resourceName, String regex, Function<String,T> mapper) {
        return stream(resourceName, regex)
                .map(stream -> stream.map(mapper).collect(Collectors.toList()))
                .toList();
    }

    /**
     * Return a List of objects of type T for the given resource name. The CSV is split on the default regex
     * @param resourceName The name of the resource
     * @param mapper The function to map a String to an object of type T
     * @return A List of objects of type T for the given resource name
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     */
    public static <T> List<T> flatList(String resourceName, Function<String,T> mapper) {
        return flatList(resourceName, DEFAULT_REGEX, mapper);
    }

    /**
     * Return a List of objects of type T for the given resource name. The CSV is split on the given regex
     * @param resourceName The name of the resource
     * @param regex The regex to split the CSV
     * @param mapper The function to map a String to an object of type T
     * @return A List of objects of type T for the given resource name
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     */
    public static <T> List<T> flatList(String resourceName, String regex, Function<String,T> mapper) {
        return stream(resourceName, regex)
                .flatMap(stream -> stream.map(mapper))
                .toList();
    }

    /**
     * Return a Stream of Stream of Strings for the given resource name. The CSV is split on the given regex
     * @param resourceName The name of the resource
     * @param regex The regex to split the CSV
     * @return A Stream of Stream of Strings for the given resource name
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     */
    private static Stream<Stream<String>> stream(String resourceName, String regex) {
        return ResourceLines.stream(resourceName)
                .map(line -> line.split(regex))
                .map(Arrays::stream);
    }
}
