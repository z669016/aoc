package com.putoet.grid;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * A (comparable) point in a 3D grid. Points are by default ordered by Z, then by Y and then by X.
 * @param x
 * @param y
 * @param z
 */
public record Point3D(int x, int y, int z) implements Comparable<Point3D> {
    /**
     * The origin of the grid.
     */
    public static final Point3D ORIGIN = Point3D.of(0, 0, 0);

    /**
     * Factory method to create a new point.
     */
    public static Point3D of(int x, int y, int z) {
        return new Point3D(x, y, z);
    }

    /**
     * Add another point to this point.
     * @param other The other point to add, must be not null.
     * @return A new point that is the sum of this point and the other point.
     */
    public Point3D add(Point3D other) {
        Objects.requireNonNull(other);

        return new Point3D(x + other.x, y + other.y, z + other.z);
    }

    /**
     * Transform this point using the given transformer function. The transformer function is applied to each coordinate
     * (x, y, and z) individually to calculate the new coordinate.
     * @param transformer The transformer function, must be not null.
     * @return A new point that is the transformed point.
     */
    public Point3D transform(Function<Integer, Integer> transformer) {
        Objects.requireNonNull(transformer);

        return new Point3D(transformer.apply(x), transformer.apply(y), transformer.apply(z));
    }

    /**
     * Subtract another point from this point.
     * @param other The other point to subtract, must be not null.
     * @return A new point that is the difference of this point and the other point.
     */
    public Point3D sub(Point3D other) {
        Objects.requireNonNull(other);

        return new Point3D(x - other.x, y - other.y, z - other.z);
    }

    /**
     * Return the list of adjacent cardinal points in a square grid.
     * @return A list of adjacent points.
     */
    public List<Point3D> adjacent() {
        return Points3D.directionsSquare().stream().map(p -> p.add(this)).toList();
    }

    /**
     * Return the list of adjacent points in a grid using the provided directions.
     * @param directions The directions to use to calculate the adjacent points, must be not null.
     * @return A list of adjacent points.
     */
    public List<Point3D> adjacent(List<Point3D> directions) {
        Objects.requireNonNull(directions);

        return directions.stream().map(p -> p.add(this)).toList();
    }

    /**
     * Calculate the Manhattan distance between this point and the origin (0, 0, 0).
     * @return The Manhattan distance.
     */
    public int manhattanDistance() {
        return manhattanDistance(Point3D.ORIGIN);
    }

    /**
     * Calculate the Manhattan distance between this point and the other point.
     * @param other The other point, must be not null.
     * @return The Manhattan distance.
     */
    public int manhattanDistance(Point3D other) {
        Objects.requireNonNull(other);

        return Math.abs(x - other.x) + (Math.abs(y - other.y) + Math.abs(z - other.z));
    }

    /**
     * Calculate the Euclidean distance between this point and the origin (0, 0, 0).
     * @return The Euclidean distance.
     */
    public double euclideanDistance() {
        return euclideanDistance(Point3D.ORIGIN);
    }

    /**
     * Calculate the Euclidean distance between this point and the other point.
     * @param other The other point, must be not null.
     * @return The Euclidean distance.
     */
    public double euclideanDistance(Point3D other) {
        Objects.requireNonNull(other);

        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int compareTo(Point3D other) {
        return Comparator.comparing(Point3D::z)
                .thenComparing(Point3D::y)
                .thenComparing(Point3D::x)
                .compare(this, other);
    }
}
