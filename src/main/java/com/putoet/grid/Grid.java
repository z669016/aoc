package com.putoet.grid;

import java.util.*;
import java.util.function.Predicate;

public class Grid implements GridType {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final char[][] grid;

    public Grid(char[][] grid) {
        this(0, grid[0].length, 0, grid.length, grid);
    }

    public Grid(int minX, int maxX, int minY, int maxY, char[][] grid) {
        assert grid != null;
        assert grid.length == maxY - minY;
        for (var row : grid)
            assert row.length == maxX - minX;

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.grid = grid;
    }

    public Grid copy() {
        return new Grid(minX, maxX, minY, maxY, GridUtils.copy(grid));
    }

    @Override
    public void set(int x, int y, char c) {
        assert x >= minX && x < maxX;
        assert y >= minY && y < maxY;

        grid[y - minY][x - minX] = c;
    }

    @Override
    public char get(int x, int y) {
        assert x >= minX && x < maxX;
        assert y >= minY && y < maxY;

        return grid[y - minY][x - minX];
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= minX && x < maxX && y >= minY && y < maxY;
    }

    @Override
    public int minX() {
        return minX;
    }

    @Override
    public int maxX() {
        return maxX;
    }

    @Override
    public int minY() {
        return minY;
    }

    @Override
    public int maxY() {
        return maxY;
    }

    @Override
    public int width() {
        return Math.abs(maxX - minX);
    }

    @Override
    public int height() {
        return Math.abs(maxY - minY);
    }

    public char[][] grid() {
        return grid;
    }

    public Grid flipHorizontally() {
        return new Grid(minX, maxX, minY, maxY, GridUtils.flipHorizontally(grid));
    }

    public Grid flipVertically() {
        return new Grid(minX, maxX, minY, maxY, GridUtils.flipVertically(grid));
    }

    public Grid rotate() {
        //noinspection SuspiciousNameCombination
        return new Grid(minY, maxY, minX, maxX, GridUtils.rotate(grid));
    }

    public long count(char toCount) {
        return GridUtils.count(grid, toCount);
    }

    public long count(Predicate<Integer> filter) {
        return GridUtils.count(grid, filter);
    }

    @Override
    public Optional<Point> findFirst(Predicate<Character> predicate) {
        for (var y = minY; y < maxY; y++)
            for (var x = minX; x < maxX; x++)
                if (predicate.test(grid[y][x]))
                    return Optional.of(Point.of(x, y));

        return Optional.empty();
    }

    @Override
    public List<Point> findAll(Predicate<Character> predicate) {
        final var found = new ArrayList<Point>();

        for (var y = minY; y < maxY; y++)
            for (var x = minX; x < maxX; x++)
                if (predicate.test(grid[y][x]))
                    found.add(Point.of(x, y));

        return found;
    }

    @Override
    public int hashCode() {
        var result = Objects.hash(minX, maxX, minY, maxY);
        result = 31 * result + Arrays.deepHashCode(grid);
        return result;
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        sb.append(String.format("(%d,%d)..(%d,%d)\n", minX, minY, maxX, maxY));
        sb.append(" ".repeat(3));
        for (var i = minX; i < maxX / 10 + 1; i++) {
            sb.append(i);
            sb.append(" ".repeat(9));
        }

        sb.append("\n").append(" ".repeat(3));
        for (var i = minX; i < maxX; i++)
            sb.append(i % 10);
        sb.append("\n");

        final var list = GridUtils.toList(grid);
        for (var i = 0; i < list.size(); i++)
            sb.append(String.format("%02d ", i % 100)).append(list.get(i)).append("\n");

        return sb.toString();
    }
}
