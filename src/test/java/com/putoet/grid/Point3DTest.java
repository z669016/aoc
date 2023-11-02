package com.putoet.grid;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Point3DTest {

    @Test
    void manhattanDistance() {
        assertEquals(3, Point3D.of(1, 1, 1).manhattanDistance());

        assertEquals(4, Point3D.ORIGIN.manhattanDistance(Point3D.of(0, 1, 3)));
        assertEquals(6, Point3D.ORIGIN.manhattanDistance(Point3D.of(-4, -2, 0)));
        assertEquals(10, Point3D.ORIGIN.manhattanDistance(Point3D.of(-3, 3, 4)));
    }

    @Test
    public void euclideanDistance() {
        assertEquals(5.0, Point3D.of(3, 4, 0).euclideanDistance());
    }

    @Test
    void add() {
        assertEquals(Point3D.of(3, 7, 1), Point3D.of(1, 9, -2).add(Point3D.of(2, -2, 3)));
    }

    @Test
    void sub() {
        assertEquals(Point3D.of(-1, 11, 2), Point3D.of(1, 9, 5).sub(Point3D.of(2, -2, 3)));
    }

    @Test
    void transform() {
        assertEquals(Point3D.of(9, 6, 21), Point3D.of(3, 2, 7).transform(x -> x * 3));
    }

    @Test
    void adjacent() {
        var adjacent = Point3D.ORIGIN.adjacent();
        assertEquals(6, adjacent.size());
        assertTrue(adjacent.containsAll(List.of(
                Point3D.of(1, 0, 0),
                Point3D.of(0, 1, 0),
                Point3D.of(0, 0, 1),
                Point3D.of(-1, 0, 0),
                Point3D.of(0, -1, 0),
                Point3D.of(0, 0, -1)
        )));

        adjacent = Point3D.ORIGIN.adjacent(Points3D.directionsAll());
        assertEquals(26, adjacent.size());
    }

    @Test
    void compareTo() {
        final List<Point3D> unsorted = new ArrayList<>(List.of(
                Point3D.of(0, 1, 0),
                Point3D.ORIGIN,
                Point3D.of(-1, 1, 0),
                Point3D.of(0, -1, 0),
                Point3D.of(2, 2, 0)
        ));
        final List<Point3D> sorted = List.of(
                Point3D.of(0, -1, 0),
                Point3D.ORIGIN,
                Point3D.of(-1, 1, 0),
                Point3D.of(0, 1, 0),
                Point3D.of(2, 2, 0)
        );

        Collections.sort(unsorted);
        assertEquals(sorted, unsorted);
    }
}