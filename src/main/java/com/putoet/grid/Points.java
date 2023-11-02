package com.putoet.grid;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public final class Points {
    private Points() {}

    public static List<Point> directionsSquare() {
        return List.of(
                Point.EAST,
                Point.NORTH,
                Point.WEST,
                Point.SOUTH
        );
    }

    public static List<Point> directionsAll() {
        return List.of(
                Point.EAST,
                Point.NORTH,
                Point.WEST,
                Point.SOUTH,
                Point.NORTH_EAST,
                Point.NORTH_WEST,
                Point.SOUTH_EAST,
                Point.SOUTH_WEST
        );
    }


    static int min(Collection<Point> points, Function<Point,Integer> extract) {
        assert !points.isEmpty();

        return points.stream().mapToInt(extract::apply).min().getAsInt();
    }

    static int max(Collection<Point> points, Function<Point,Integer> extract) {
        assert !points.isEmpty();

        return points.stream().mapToInt(extract::apply).max().getAsInt();
    }

    static Point topLeft(Collection<Point> points) {
        assert !points.isEmpty();

        return Point.of(min(points, Point::x), min(points, Point::y));
    }

    static Point bottomRight(Collection<Point> points) {
        assert !points.isEmpty();

        return Point.of(max(points, Point::x), max(points, Point::y));
    }
}
