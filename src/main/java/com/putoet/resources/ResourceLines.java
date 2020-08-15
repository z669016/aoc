package com.putoet.resources;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
            final URL url = ResourceLines.class.getResource(resourceName);
            final Path path = Paths.get(url.toURI());
            return Files.lines(path);
        } catch (URISyntaxException | IOException exc) {
            throw new IllegalArgumentException("Invalid resource name '" + resourceName + "'", exc);
        }
    }

    public static List<String> list(String resourceName) {
        return stream(resourceName).collect(Collectors.toList());
    }
}
