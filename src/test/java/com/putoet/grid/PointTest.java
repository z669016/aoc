package com.putoet.grid;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PointTest {

    @Test
    void manhattanDistance() {
        assertEquals(2, Point.ORIGIN.manhattanDistance(Point.of(1, 1)));
        assertEquals(1, Point.ORIGIN.manhattanDistance(Point.of(0, 1)));
        assertEquals(8, Point.ORIGIN.manhattanDistance(Point.of(-4, -4)));
        assertEquals(6, Point.ORIGIN.manhattanDistance(Point.of(-3, 3)));
    }

    @Test
    public void distance() {
        assertEquals(5.0, Point.of(3, 4).euclideanDistance());
    }

    @Test
    void add() {
        assertEquals(Point.of(3, 7), Point.of(1, 9).add(Point.of(2, -2)));
    }

    @Test
    void sub() {
        assertEquals(Point.of(-1, 11), Point.of(1, 9).sub(Point.of(2, -2)));
    }

    @Test
    void adjacent() {
        final List<Point> adjacent = Point.ORIGIN.adjacend();
        assertEquals(8, adjacent.size());
        assertTrue(adjacent.containsAll(List.of(
                Point.of(1, 0),
                Point.of(1, 1),
                Point.of(0, 1),
                Point.of(-1, 1),
                Point.of(-1, 0),
                Point.of(-1, -1),
                Point.of(0, -1),
                Point.of(1, -1)
        )));
    }

    @Test
    void compareTo() {
        final List<Point> unsorted = new ArrayList<>(List.of(
                Point.of(0, 1),
                Point.ORIGIN,
                Point.of(-1, 1),
                Point.of(0, -1),
                Point.of(2, 2)
        ));
        final List<Point> sorted = List.of(
                Point.of(0, -1),
                Point.ORIGIN,
                Point.of(-1, 1),
                Point.of(0, 1),
                Point.of(2, 2)
        );

        Collections.sort(unsorted);
        assertEquals(sorted, unsorted);
    }
}