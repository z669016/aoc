package com.putoet.grid;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void manhattanDistance() {
        assertEquals(2, Point.of(1, 1).manhattanDistance());

        assertEquals(1, Point.ORIGIN.manhattanDistance(Point.of(0, 1)));
        assertEquals(8, Point.ORIGIN.manhattanDistance(Point.of(-4, -4)));
        assertEquals(6, Point.ORIGIN.manhattanDistance(Point.of(-3, 3)));
    }

    @Test
    void euclideanDistance() {
        assertEquals(5.0, Point.of(3, 4).euclideanDistance());
    }

    @Test
    public void distance() {
        assertEquals(5.0, Point.of(3, 4).euclideanDistance());
    }

    @Test
    void add() {
        assertEquals(Point.of(3, 7), Point.of(1, 9).add(Point.of(2, -2)));
        assertThrows(NullPointerException.class, () -> Point.of(1, 1).add(null));
    }

    @Test
    void sub() {
        assertEquals(Point.of(-1, 11), Point.of(1, 9).sub(Point.of(2, -2)));
    }

    @Test
    void transform() {
        assertEquals(Point.of(9, 6), Point.of(3, 2).transform(x -> x * 3));
    }

    @Test
    void adjacent() {
        var adjacent = Point.ORIGIN.adjacent();
        assertEquals(4, adjacent.size());
        assertTrue(adjacent.containsAll(List.of(
                Point.of(1, 0),
                Point.of(0, 1),
                Point.of(-1, 0),
                Point.of(0, -1)
        )));

        adjacent = Point.ORIGIN.adjacent(Points.directionsAll());
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
        final var unsorted = new ArrayList<>(List.of(
                Point.of(0, 1),
                Point.ORIGIN,
                Point.of(-1, 1),
                Point.of(0, -1),
                Point.of(2, 2)
        ));
        final var sorted = List.of(
                Point.of(0, -1),
                Point.ORIGIN,
                Point.of(-1, 1),
                Point.of(0, 1),
                Point.of(2, 2)
        );

        Collections.sort(unsorted);
        assertEquals(sorted, unsorted);

        assertThrows(NullPointerException.class, () -> Point.ORIGIN.compareTo(null));
    }
}