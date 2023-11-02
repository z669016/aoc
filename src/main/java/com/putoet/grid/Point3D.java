package com.putoet.grid;

import java.util.List;
import java.util.function.Function;

public record Point3D(int x, int y, int z) implements Comparable<Point3D> {
    public static final Point3D ORIGIN = Point3D.of(0, 0, 0);

    public static Point3D of(int x, int y, int z) {
        return new Point3D(x, y, z);
    }

    public Point3D add(Point3D other) {
        assert other != null;

        return new Point3D(x + other.x, y + other.y, z + other.z);
    }

    public Point3D transform(Function<Integer, Integer> transformer) {
        assert transformer != null;

        return new Point3D(transformer.apply(x), transformer.apply(y), transformer.apply(z));
    }

    public Point3D sub(Point3D other) {
        assert other != null;

        return new Point3D(x - other.x, y - other.y, z - other.z);
    }

    public List<Point3D> adjacent() {
        return Points3D.directionsSquare().stream().map(p -> p.add(this)).toList();
    }

    public List<Point3D> adjacent(List<Point3D> directions) {
        return directions.stream().map(p -> p.add(this)).toList();
    }

    public int manhattanDistance(Point3D other) {
        return Math.abs(x - other.x) + (Math.abs(y - other.y) + Math.abs(z - other.z));
    }

    public int manhattanDistance() {
        return manhattanDistance(Point3D.ORIGIN);
    }

    public double euclideanDistance() {
        return euclideanDistance(Point3D.ORIGIN);
    }

    public double euclideanDistance(Point3D other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int compareTo(Point3D other) {
        return (z != other.z) ? Integer.compare(z, other.z) :
                (y != other.y) ? Integer.compare(y, other.y) : Integer.compare(x, other.x);
    }
}
