package com.putoet.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public record Point(int x, int y) implements Comparable<Point> {
    public static final Point ORIGIN = Point.of(0, 0);

    public static final Point NORTH = Point.of(0, 1);
    public static final Point NORTH_EAST = Point.of(1, 1);
    public static final Point EAST = Point.of(1, 0);
    public static final Point SOUTH_EAST = Point.of(1, -1);
    public static final Point SOUTH = Point.of(0, -1);
    public static final Point SOUTH_WEST = Point.of(-1, -1);
    public static final Point WEST = Point.of(-1, 0);
    public static final Point NORTH_WEST = Point.of(-1, 1);
    private static List<Point> strictDirections;
    private static List<Point> allDirections;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    public static List<Point> directions(boolean strict) {
        if (strict) {
            if (strictDirections == null)
                strictDirections = List.of(EAST, NORTH, WEST, SOUTH);
            return strictDirections;
        }

        if (allDirections == null) {
            allDirections = new ArrayList<>(directions(true));
            allDirections.add(NORTH_EAST);
            allDirections.add(NORTH_WEST);
            allDirections.add(SOUTH_EAST);
            allDirections.add(SOUTH_WEST);
            allDirections = Collections.unmodifiableList(allDirections);
        }
        return allDirections;
    }

    public Point add(Point other) {
        assert other != null;

        return new Point(x + other.x, y + other.y);
    }

    public Point sub(Point other) {
        assert other != null;

        return new Point(x - other.x, y - other.y);
    }

    public Point transform(Function<Integer,Integer> transformer) {
        assert transformer != null;

        return new Point(transformer.apply(x), transformer.apply(y));
    }


    public List<Point> adjacent() {
        return directions(false).stream().map(p -> p.add(this)).collect(Collectors.toList());
    }

    public int manhattanDistance() {
        return manhattanDistance(Point.ORIGIN);
    }

    public int manhattanDistance(Point other) {
        return Math.abs(x - other.x) + (Math.abs(y - other.y));
    }

    public double euclideanDistance() {
        return euclideanDistance(Point.ORIGIN);
    }

    public double euclideanDistance(Point other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Point other) {
        return (y == other.y) ? Integer.compare(x, other.x) : Integer.compare(y, other.y);
    }
}
