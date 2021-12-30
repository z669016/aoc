package com.putoet.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record Point3D(int x, int y, int z) implements Comparable<Point3D> {
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

    static Point3D roll(Point3D point) { return Point3D.of(point.x(), point.z(), -point.y()); }
    static Point3D turn(Point3D point) { return Point3D.of(-point.y(), point.x(), point.z()); }

    // https://stackoverflow.com/questions/16452383/how-to-get-all-24-rotations-of-a-3-dimensional-array
    public static List<Point3D> rotations(Point3D point) {
        final List<Point3D> rotations = new ArrayList<>();
        for (int flip = 0; flip < 2; flip++) {
            for (int rollCount = 0; rollCount < 3; rollCount++) {
                point = roll(point);
                rotations.add(point);
                for (int turnCount = 0; turnCount < 3; turnCount++) {
                    point = turn(point);
                    rotations.add(point);
                }
            }
            point = roll(turn(roll(point)));
        }
        return rotations;
    }

    public Point3D add(Point3D other) {
        assert other != null;

        return new Point3D(x + other.x, y + other.y, z + other.z);
    }

    public Point3D sub(Point3D other) {
        assert other != null;

        return new Point3D(x - other.x, y - other.y, z - other.z);
    }

    public List<Point3D> adjacent() {
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

    @Override
    public int compareTo(Point3D other) {
        return (z != other.z) ? Integer.compare(z, other.z) :
                (y != other.y) ? Integer.compare(y, other.y): Integer.compare(x, other.x);
    }
}
