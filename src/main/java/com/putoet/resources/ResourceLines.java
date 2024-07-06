package com.putoet.resources;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ResourceLines provides a simple way to read resources from the classpath and convert them to a List, Set or Stream of
 * lines. The resource name should start with a '/' and is relative to the classpath.
 */
public class ResourceLines {
    /**
     * Return an InputStream for the given resource name.
     * @param resourceName The name of the resource
     * @return An InputStream for the given resource name
     */
    public static InputStream inputStream(String resourceName) {
        return ResourceLines.class.getResourceAsStream(resourceName);
    }

    /**
     * Return an InputStream for the given file.
     * @param file The file to read
     * @throws IllegalArgumentException if the file does not exist
     * @return An InputStream for the given file
     */
    public static InputStream inputStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException exc) {
            throw new IllegalArgumentException("Invalid file '" + file.getAbsolutePath() + "'", exc);
        }
    }

    /**
     * Return a Stream of lines for the given resource name.
     * @param resourceName The name of the resource
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     * @return A Stream of lines for the given resource name
     */
    public static Stream<String> stream(String resourceName) {
        try {
            final var url = ResourceLines.class.getResource(resourceName);
            if (url == null)
                throw new IllegalArgumentException("Invalid resource name '" + resourceName + "'");

            final var path = Paths.get(url.toURI());
            return Files.lines(path);
        } catch (URISyntaxException | IOException exc) {
            throw new IllegalArgumentException("Invalid resource name '" + resourceName + "'", exc);
        }
    }

    /**
     * Return a Stream of lines (String) for the given file.
     * @param resourceName The file to read
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     * @return A Stream of lines for the given file
     */
    public static List<String> list(String resourceName) {
        return stream(resourceName).toList();
    }

    /**
     * Return a Set of lines (String) for the given resource name.
     * @param resourceName The name of the resource
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     * @return A Set of lines for the given resource name
     */
    public static Set<String> set(String resourceName) {
        return stream(resourceName).collect(Collectors.toSet());
    }

    /**
     * Return a String with all lines concatenated for the given resource name.
     * @param resourceName The name of the resource
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     * @return A String with all lines concatenated for the given resource name
     */
    public static String line(String resourceName) {
        return stream(resourceName).collect(Collectors.joining());
    }

    /**
     * Return a List of objects of type T for the given resource name, using the provided function to convert a line
     * to an object of type T.
     * @param resourceName The name of the resource
     * @param of The function to convert a line to an object
     * @throws IllegalArgumentException if the resource does not exist or the URL is invalid
     * @return A List of objects for the given resource name
     */
    public static <T> List<T> list(String resourceName, Function<String,T> of) {
        return stream(resourceName).map(of).toList();
    }
}
