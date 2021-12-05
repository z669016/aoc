package com.putoet.grid;

import java.util.Collection;
import java.util.function.Function;

public interface Points {
    static int min(Collection<Point> points, Function<Point,Integer> extract) {
        assert points.size() > 0;

        return points.stream().mapToInt(extract::apply).min().getAsInt();
    }

    static int max(Collection<Point> points, Function<Point,Integer> extract) {
        assert points.size() > 0;

        return points.stream().mapToInt(extract::apply).max().getAsInt();
    }

    static Point topLeft(Collection<Point> points) {
        assert points.size() > 0;

        return Point.of(min(points, Point::x), min(points, Point::y));
    }

    static Point bottomRight(Collection<Point> points) {
        assert points.size() > 0;

        return Point.of(max(points, Point::x), max(points, Point::y));
    }
}
