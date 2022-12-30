package com.putoet.resources;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ResourceLinesTest {

    @Test
    void inputStream() {
        assertNotNull(ResourceLines.inputStream("/file.txt"));
    }

    @Test
    void fileInputStream() {
        assertNotNull(ResourceLines.inputStream(new File("src/test/resources/file.txt")));
    }

    @Test
    void stream() {
        assertNotNull(ResourceLines.stream("/file.txt"));
    }

    @Test
    void list() {
        assertEquals(List.of("1", "2", "3", "4", "5"), ResourceLines.list("/file.txt"));
    }

    @Test
    void set() {
        assertEquals(Set.of("1", "2", "3", "4", "5"), ResourceLines.set("/file.txt"));
    }

    @Test
    void line() {
        assertEquals("12345", ResourceLines.line("/file.txt"));
    }

    @Test
    void genericList() {
        assertEquals(List.of(1, 2, 3, 4, 5), ResourceLines.list("/file.txt", Integer::parseInt));
    }
}