package com.putoet.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record Point3D(int x, int y, int z) {
    public static final Point3D ORIGIN = Point3D.of(0, 0, 0);
    private static List<Point3D> strictDirections;
    private static List<Point3D> allDirections;

    public static Point3D of(int x, int y, int z) {
        return new Point3D(x, y, z);
    }

    public static List<Point3D> directions(boolean strict) {
        if (strict) {
            if (strictDirections == null)
                strictDirections = List.of(
                        Point3D.of(1, 0, 0),
                        Point3D.of(0, 1, 0),
                        Point3D.of(-1, 0, 0),
                        Point3D.of(0, -1, 0),
                        Point3D.of(0, 0, 1),
                        Point3D.of(0, 0, -1)
                );
            return strictDirections;
        }

        if (allDirections == null) {
            allDirections = new ArrayList<>(directions(true));
            allDirections.add(Point3D.of(1, 0, -1));
            allDirections.add(Point3D.of(0, 1, -1));
            allDirections.add(Point3D.of(-1, 0, -1));
            allDirections.add(Point3D.of(0, -1, -1));
            allDirections.add(Point3D.of(1, 1, -1));
            allDirections.add(Point3D.of(-1, 1, -1));
            allDirections.add(Point3D.of(1, -1, -1));
            allDirections.add(Point3D.of(-1, -1, -1));
            allDirections.add(Point3D.of(1, 1, 0));
            allDirections.add(Point3D.of(-1, 1, 0));
            allDirections.add(Point3D.of(1, -1, 0));
            allDirections.add(Point3D.of(-1, -1, 0));
            allDirections.add(Point3D.of(1, 0, 1));
            allDirections.add(Point3D.of(0, 1, 1));
            allDirections.add(Point3D.of(-1, 0, 1));
            allDirections.add(Point3D.of(0, -1, 1));
            allDirections.add(Point3D.of(1, 1, 1));
            allDirections.add(Point3D.of(-1, 1, 1));
            allDirections.add(Point3D.of(1, -1, 1));
            allDirections.add(Point3D.of(-1, -1, 1));
            allDirections = Collections.unmodifiableList(allDirections);
        }
        return allDirections;
    }

    public Point3D add(Point3D other) {
        assert other != null;

        return new Point3D(x + other.x, y + other.y, z + other.z);
    }

    public List<Point3D> adjacend() {
        return directions(false).stream().map(p -> p.add(this)).collect(Collectors.toList());
    }

    public int manhattanDistance(Point3D other) {
        return Math.abs(x - other.x) + (Math.abs(y - other.y) + Math.abs(z - other.z));
    }

    public int manhattanDistance() {
        return manhattanDistance(Point3D.ORIGIN);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
