package com.putoet.grid;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A (comparable) point in a 2D grid. Points are by default ordered by Y and then by X.
 * @param x
 * @param y
 */
public record Point(int x, int y) implements Comparable<Point> {
    /**
     * The origin of the grid.
     */
    public static final Point ORIGIN = Point.of(0, 0);

    /**
     * The eight directions in a square grid.
     */
    public static final Point NORTH = Point.of(0, 1);
    public static final Point NORTH_EAST = Point.of(1, 1);
    public static final Point EAST = Point.of(1, 0);
    public static final Point SOUTH_EAST = Point.of(1, -1);
    public static final Point SOUTH = Point.of(0, -1);
    public static final Point SOUTH_WEST = Point.of(-1, -1);
    public static final Point WEST = Point.of(-1, 0);
    public static final Point NORTH_WEST = Point.of(-1, 1);

    /**
     * Factory method to create a new point.
     */
    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    /**
     * Add another point to this point.
     * @param other The other point to add, must be not null.
     * @return A new point that is the sum of this point and the other point.
     */
    public Point add(Point other) {
        Objects.requireNonNull(other);

        return new Point(x + other.x, y + other.y);
    }

    /**
     * Subtract another point from this point.
     * @param other The other point to subtract, must be not null.
     * @return A new point that is the difference of this point and the other point.
     */
    public Point sub(Point other) {
        Objects.requireNonNull(other);

        return new Point(x - other.x, y - other.y);
    }

    /**
     * Transform this point using the provided transformer.
     * @param transformer The transformer to apply to the x and y values, must be not null.
     * @return A new point that is the result of applying the transformer to the x and y values of this point.
     */
    public Point transform(Function<Integer,Integer> transformer) {
        Objects.requireNonNull(transformer);

        return new Point(transformer.apply(x), transformer.apply(y));
    }

    /**
     * Return the list of (cardinal) adjacent points in a square grid.
     * @return A list of adjacent points.
     */
    public List<Point> adjacent() {
        return adjacent(Points.directionsSquare());
    }

    /**
     * Return the list of adjacent points in a grid using the provided directions.
     * @param directions The directions to use to calculate the adjacent points, must be not null.
     * @return A list of adjacent points.
     */
    public List<Point> adjacent(List<Point> directions) {
        Objects.requireNonNull(directions);

        return directions.stream().map(p -> p.add(this)).collect(Collectors.toList());
    }

    /**
     * Calculate the manhattan distance from this point to the origin.
     * @return The manhattan distance from this point to the origin.
     */
    public int manhattanDistance() {
        return manhattanDistance(Point.ORIGIN);
    }

    /**
     * Calculate the manhattan distance from this point to another point.
     * @param other The other point, must be not null
     * @return The manhattan distance from this point to the other point.
     */
    public int manhattanDistance(Point other) {
        Objects.requireNonNull(other);

        return Math.abs(x - other.x) + (Math.abs(y - other.y));
    }

    /**
     * Calculate the Euclidean distance from this point to the origin.
     * @return The Euclidean distance from this point to the origin.
     */
    public double euclideanDistance() {
        return euclideanDistance(Point.ORIGIN);
    }

    /**
     * Calculate the Euclidean distance from this point to another point.
     * @param other The other point, must be not null
     * @return The Euclidean distance from this point to the other point.
     */
    public double euclideanDistance(Point other) {
        Objects.requireNonNull(other);

        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Point other) {
        Objects.requireNonNull(other);

        return Comparator.comparing(Point::y)
                .thenComparing(Point::x)
                .compare(this, other);
    }
}
