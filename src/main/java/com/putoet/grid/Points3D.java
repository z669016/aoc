package com.putoet.grid;

import java.util.ArrayList;
import java.util.List;

public final class Points3D {
    private Points3D() {}

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

    static Point3D roll(Point3D point) {
        return Point3D.of(point.x(), point.z(), -point.y());
    }

    static Point3D turn(Point3D point) {
        return Point3D.of(-point.y(), point.x(), point.z());
    }

    // https://stackoverflow.com/questions/16452383/how-to-get-all-24-rotations-of-a-3-dimensional-array
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
