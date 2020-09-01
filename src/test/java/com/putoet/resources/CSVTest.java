package com.putoet.resources;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {
    @Test
    void list() {
        final List<List<String>> list = CSV.list("/csv.txt");
        assertEquals(List.of(List.of("1", "2", "3"), List.of("4", "5", "6")), list);
    }

    @Test
    void listTab() {
        final List<List<String>> list = CSV.list("/csv-tab.txt", "\t");
        assertEquals(List.of(List.of("1", "2", "3"), List.of("4", "5", "6")), list);
    }

    @Test
    void flatList() {
        final List<String> list = CSV.flatList("/csv.txt");
        assertEquals(List.of("1", "2", "3", "4", "5", "6"), list);
    }

    @Test
    void mapper() {
        final List<List<Integer>> list = CSV.list("/csv.txt", Integer::parseInt);
        assertEquals(List.of(List.of(1, 2, 3), List.of(4, 5, 6)), list);
    }

    @Test
    void flatMapper() {
        final List<Integer> list = CSV.flatList("/csv.txt", Integer::parseInt);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), list);
    }
}