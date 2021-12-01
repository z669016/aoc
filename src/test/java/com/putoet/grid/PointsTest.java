package com.putoet.grid;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PointsTest {
    private static final Set<Point> POINTS = Set.of(
            Point.ORIGIN,
            Point.of(-3, 3),
            Point.of(0, 5),
            Point.of(-7, 1),
            Point.of(8, -6)
    );

    @Test
    void min() {
        assertEquals(-7, Points.min(POINTS, p -> p.x));
        assertEquals(-6, Points.min(POINTS, p -> p.y));
    }

    @Test
    void max() {
        assertEquals(8, Points.max(POINTS, p -> p.x));
        assertEquals(5, Points.max(POINTS, p -> p.y));
    }

    @Test
    void topLeft() {
        assertEquals(Point.of(-7, -6), Points.topLeft(POINTS));
    }

    @Test
    void bottomRight() {
        assertEquals(Point.of(8, 5), Points.bottomRight(POINTS));
    }
}