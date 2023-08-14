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

public class ResourceLines {
    public static InputStream inputStream(String resourceName) {
        return ResourceLines.class.getResourceAsStream(resourceName);
    }

    public static InputStream inputStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException exc) {
            throw new IllegalArgumentException("Invalid file '" + file.getAbsolutePath() + "'", exc);
        }
    }

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

    public static List<String> list(String resourceName) {
        return stream(resourceName).toList();
    }

    public static Set<String> set(String resourceName) {
        return stream(resourceName).collect(Collectors.toSet());
    }

    public static String line(String resourceName) {
        return stream(resourceName).collect(Collectors.joining());
    }

    public static <T> List<T> list(String resourceName, Function<String,T> of) {
        return stream(resourceName).map(of).toList();
    }
}
