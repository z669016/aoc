package com.putoet.grid;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for working with 3D points.
 */
public final class Points3D {
    private Points3D() {}

    /**
     * Returns a list of points representing the six cardinal directions.
     * @return A list of points representing the six cardinal directions.
     */
    public static List<Point3D> directionsSquare() {
        return List.of(
                Point3D.of(1, 0, 0),
                Point3D.of(0, 1, 0),
                Point3D.of(-1, 0, 0),
                Point3D.of(0, -1, 0),
                Point3D.of(0, 0, 1),
                Point3D.of(0, 0, -1)
        );
    }

    /**
     * Returns a list of points representing the twenty-six cardinal and diagonal directions.
     * @return A list of points representing the twenty-six cardinal and diagonal directions.
     */
    public static List<Point3D> directionsAll() {
        return List.of(
                Point3D.of(1, 0, 0),
                Point3D.of(0, 1, 0),
                Point3D.of(-1, 0, 0),
                Point3D.of(0, -1, 0),
                Point3D.of(0, 0, 1),
                Point3D.of(0, 0, -1),
                Point3D.of(1, 0, -1),
                Point3D.of(0, 1, -1),
                Point3D.of(-1, 0, -1),
                Point3D.of(0, -1, -1),
                Point3D.of(1, 1, -1),
                Point3D.of(-1, 1, -1),
                Point3D.of(1, -1, -1),
                Point3D.of(-1, -1, -1),
                Point3D.of(1, 1, 0),
                Point3D.of(-1, 1, 0),
                Point3D.of(1, -1, 0),
                Point3D.of(-1, -1, 0),
                Point3D.of(1, 0, 1),
                Point3D.of(0, 1, 1),
                Point3D.of(-1, 0, 1),
                Point3D.of(0, -1, 1),
                Point3D.of(1, 1, 1),
                Point3D.of(-1, 1, 1),
                Point3D.of(1, -1, 1),
                Point3D.of(-1, -1, 1)
        );
    }

    /**
     * Returns the rolled over point of the given point.
     * @param point The point to roll over.
     * @return The rolled over point.
     */
    static Point3D roll(Point3D point) {
        return Point3D.of(point.x(), point.z(), -point.y());
    }

    /**
     * Returns the turned point of the given point.
     * @param point The point to turn.
     * @return The turned point.
     */
    static Point3D turn(Point3D point) {
        return Point3D.of(-point.y(), point.x(), point.z());
    }

    /**
     * Returns a list of all 24 rotations of the given point.
     * @see <a href=https://stackoverflow.com/questions/16452383/how-to-get-all-24-rotations-of-a-3-dimensional-array>
     *     how to get all 24 rotations of a 3 dimensional array</a>
     * @param point The point to rotate.
     * @return A list of all 24 rotations of the given point.
     */
    public static List<Point3D> rotations(Point3D point) {
        final var rotations = new ArrayList<Point3D>();
        for (var flip = 0; flip < 2; flip++) {
            for (var rollCount = 0; rollCount < 3; rollCount++) {
                point = roll(point);
                rotations.add(point);
                for (var turnCount = 0; turnCount < 3; turnCount++) {
                    point = turn(point);
                    rotations.add(point);
                }
            }
            point = roll(turn(roll(point)));
        }
        return rotations;
    }
}
