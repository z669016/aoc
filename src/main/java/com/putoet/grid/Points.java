package com.putoet.grid;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * A utility class for working with points.
 */
public final class Points {
    private Points() {}

    /**
     * Returns a list of points representing the four cardinal directions.
     * @return A list of points representing the four cardinal directions.
     */
    public static List<Point> directionsSquare() {
        return List.of(
                Point.EAST,
                Point.NORTH,
                Point.WEST,
                Point.SOUTH
        );
    }

    /**
     * Returns a list of points representing the cardinal and diagonal directions.
     * @return A list of points representing the cardinal and diagonal directions.
     */
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

    /**
     * Returns the minimum value of the given points, where value is extracted using the given function. The extraction
     * function could for instance calculate the distance to the origin, or the angle from the x-axis.
     * @param points The points to calculate the minimum value from.
     * @param extract The function to extract the value from a point.
     * @return The minimum x and y values of the given points.
     */
    static int min(Collection<Point> points, Function<Point,Integer> extract) {
        assert !points.isEmpty();

        return points.stream().mapToInt(extract::apply).min().getAsInt();
    }

    /**
     * Returns the maximum value of the given points, where value is extracted using the given function. The extraction
     * function could for instance calculate the distance to the origin, or the angle from the x-axis.
     * @param points The points to calculate the maximum value from.
     * @param extract The function to extract the value from a point.
     * @return The maximum x and y values of the given points.
     */
    static int max(Collection<Point> points, Function<Point,Integer> extract) {
        assert !points.isEmpty();

        return points.stream().mapToInt(extract::apply).max().getAsInt();
    }

    /**
     * Returns the top-left point of the given points, which is the point with the minimal X and minimal Y value from
     * the collection of points.
     * @param points The points to calculate the top-left point from.
     * @return The top-left point of the given points.
     */
    static Point topLeft(Collection<Point> points) {
        assert !points.isEmpty();

        return Point.of(min(points, Point::x), min(points, Point::y));
    }

    /**
     * Returns the bottom-right point of the given points, which is the point with the maximal X and maximal Y value
     * from the collection of points.
     * @param points The points to calculate the bottom-right point from.
     * @return The bottom-right point of the given points.
     */
    static Point bottomRight(Collection<Point> points) {
        assert !points.isEmpty();

        return Point.of(max(points, Point::x), max(points, Point::y));
    }
}
