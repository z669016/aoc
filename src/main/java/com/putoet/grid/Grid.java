package com.putoet.grid;

import java.util.*;
import java.util.function.Predicate;

/**
 * A Grid represents a rectangular grid of characters. It provides methods to get and set characters, and to find
 * characters. It should be relatively easy to make it generic for any type of object, but for now it is char based.
 * The grid coordinates are adjusted based on the minimum x and y positions upon access. This means the 2d matrix
 * with the actual content is always 0-based, but the coordinates are adjusted to the minimum x and y positions.
 */
public class Grid implements GridType {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final char[][] grid;

    /**
     * Create a new Grid with the given grid
     * @param grid the grid to use
     * @throws NullPointerException if the grid is null
     */
    public Grid(char[][] grid) {
        this(0, grid[0].length, 0, grid.length, grid);
    }

    /**
     * Create a new Grid with the given grid and the given minimum and maximum x and y positions
     * @param minX the minimum x position
     * @param maxX the maximum x position
     * @param minY the minimum y position
     * @param maxY the maximum y position
     * @param grid the grid to use
     * @throws NullPointerException if the grid is null
     * @throws IllegalArgumentException if the grid is not rectangular
     */
    public Grid(int minX, int maxX, int minY, int maxY, char[][] grid) {
        Objects.requireNonNull(grid);

        if (!GridUtils.isRectangular(grid))
            throw new IllegalArgumentException("Grid is not rectangular");

        if (grid.length != maxY - minY)
            throw new IllegalArgumentException("Grid length is not correct");

        for (var row : grid)
            assert row.length == maxX - minX;

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.grid = grid;
    }

    /**
     * Create a copy of this Grid
     */
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

    /**
     * Get the grid as a 2d array of characters
     * @return the grid as a 2d array of characters
     */
    public char[][] grid() {
        return grid;
    }

    /**
     * Return a copy of this Grid which is flipped horizontally
     * @return a copy of this Grid which is flipped horizontally
     */
    public Grid flipHorizontally() {
        return new Grid(minX, maxX, minY, maxY, GridUtils.flipHorizontally(grid));
    }

    /**
     * Return a copy of this Grid which is flipped vertically
     * @return a copy of this Grid which is flipped vertically
     */
    public Grid flipVertically() {
        return new Grid(minX, maxX, minY, maxY, GridUtils.flipVertically(grid));
    }

    /**
     * Return a copy of this Grid which is rotated 90 degrees clockwise
     * @return a copy of this Grid which is rotated 90 degrees clockwise
     */
    public Grid rotate() {
        //noinspection SuspiciousNameCombination
        return new Grid(minY, maxY, minX, maxX, GridUtils.rotate(grid));
    }

    /**
     * Count how often he given character is present in the grid
     * @return the number of times the given character is present in the grid
     */
    public long count(char toCount) {
        return GridUtils.count(grid, toCount);
    }

    /**
     * Count how often the given predicate is true for the characters in the grid
     * @param filter the predicate to use, must not be null
     * @return the number of times the given predicate is true for the characters in the grid
     */
    public long count(Predicate<Integer> filter) {
        Objects.requireNonNull(filter);

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
