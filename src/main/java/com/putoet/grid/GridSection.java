package com.putoet.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * A GridSection represents a rectangular section of a larger GridType. It basically wraps the original grid without
 * copying the content. Only the {@link #copy()} method creates a new GridSection with a copy of the original grid,
 * to prevent the original to be modified after copying.
 */
public record GridSection(GridType grid, Point upperLeft, Point lowerRight) implements GridType {
    public GridSection {
        assert grid.contains(upperLeft.x(), upperLeft.y());
        assert grid.contains(lowerRight.x() - 1, lowerRight.y() - 1);
    }

    @Override
    public GridSection copy() {
        return new GridSection(grid.copy(), upperLeft, lowerRight);
    }

    @Override
    public int minX() {
        return upperLeft.x();
    }

    @Override
    public int maxX() {
        return lowerRight.x();
    }

    @Override
    public int minY() {
        return upperLeft.y();
    }

    @Override
    public int maxY() {
        return lowerRight.y();
    }

    @Override
    public int width() {
        return Math.abs(maxX() - minX());
    }

    @Override
    public int height() {
        return Math.abs(maxY() - minY());
    }

    @Override
    public void set(int x, int y, char c) {
        assert contains(x, y);

        grid.set(x, y, c);
    }

    @Override
    public char get(int x, int y) {
        assert contains(x, y);

        return grid.get(x, y);
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= minX() && x < maxX() && y >= minY() && y < maxY();
    }

    @Override
    public Optional<Point> findFirst(Predicate<Character> predicate) {
        for (var y = minY(); y < maxY(); y++)
            for (var x = minX(); x < maxX(); x++)
                if (predicate.test(grid.get(x, y)))
                    return Optional.of(Point.of(x, y));

        return Optional.empty();
    }

    @Override
    public List<Point> findAll(Predicate<Character> predicate) {
        final var found = new ArrayList<Point>();

        for (var y = minY(); y < maxY(); y++)
            for (var x = minX(); x < maxX(); x++)
                if (predicate.test(grid.get(x, y)))
                    found.add(Point.of(x, y));

        return Collections.unmodifiableList(found);
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        sb.append(String.format("(%d,%d)..(%d,%d)\n", minX(), minY(), maxX(), maxY()));
        sb.append(" ".repeat(3));
        for (var i = 0; i < width() / 10 + 1; i++) {
            sb.append(i);
            sb.append(" ".repeat(9));
        }
        sb.append("\n").append(" ".repeat(3));
        for (var i = 0; i < width() - 1; i++)
            sb.append(i % 10);
        sb.append("\n");

        for (var y = minY(); y < maxY(); y++) {
            sb.append(String.format("%02d ", y % 100));
            for (var x = minX(); x < maxX(); x++) {
                sb.append(grid.get(x, y));
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
