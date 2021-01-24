package com.putoet.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.function.Predicate;

public class Grid {
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
        for (char[] row : grid)
            assert row.length == maxX - minX;

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.grid = grid;
    }

    public Grid copy() {
        final char[][] copy = new char[maxY - minY][maxX - minX];
        for (int y = 0; y < grid.length; y++)
            System.arraycopy(grid[y], 0, copy[y], 0, grid[y].length);

        return new Grid(minX, maxX, minY, maxY, copy);
    }

    public void set(int x, int y, char c) {
        assert x >= minX && x < maxX;
        assert y >= minY && y < maxY;

        grid[y - minY][x - minX] = c;
    }

    public char get(int x, int y) {
        assert x >= minX && x < maxX;
        assert y >= minY && y < maxY;

        return grid[y - minY][x - minX];
    }

    public boolean contains(int x, int y) {
        return x >= minX && x < maxX && y >= minY && y < maxY;
    }

    public int minX() { return minX; }
    public int maxX() { return maxX; }
    public int minY() { return minY; }
    public int maxY() { return maxY; }
    public char[][] grid() { return grid; }

    public Grid flipHorizontally() {
        return new Grid(minX, maxX, minY, maxY, GridUtils.flipHorizontally(grid));
    }

    public Grid flipVertically() {
        return new Grid(minX, maxX, minY, maxY, GridUtils.flipVertically(grid));
    }

    public Grid rotate() {
        return new Grid(minY, maxY, minX, maxX, GridUtils.rotate(grid));
    }

    public long count(char toCount) {
        return GridUtils.count(grid, toCount);
    }

    public Optional<Point> findFirst(Predicate<Character> predicate) {
        for (int y = minY; y < maxY; y++)
            for (int x = minX; x < maxX; x++)
                if (predicate.test(grid[y][x]))
                    return Optional.of(Point.of(x,y));

        return Optional.empty();
    }

    public List<Point> findAll(Predicate<Character> predicate) {
        final List<Point> found = new ArrayList<>();

        for (int y = minY; y < maxY; y++)
            for (int x = minX; x < maxX; x++)
                if (predicate.test(grid[y][x]))
                    found.add(Point.of(x,y));

        return found;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("(%d,%d)..(%d,%d)\n", minX, minY, maxX, maxY));
        sb.append(" ".repeat(3));
        for (int i = 0; i < maxX / 10 + 1; i++) {
            sb.append(i);
            sb.append(" ".repeat(9));
        }
        sb.append("\n").append(" ".repeat(3));
        for (int i = 0; i < maxX - 1; i++)
            sb.append(i % 10);
        sb.append("\n");

        final List<String> list = GridUtils.toList(grid);
        for (int i = 0; i < list.size(); i++)
            sb.append(String.format("%02d ", i % 100)).append(list.get(i)).append("\n");

        return sb.toString();
    }
}
