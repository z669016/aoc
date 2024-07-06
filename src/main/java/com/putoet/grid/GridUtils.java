package com.putoet.grid;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Utility class for working with grids.
 */
public class GridUtils {
    /**
     * Create a grid from a list of strings. Each string is converted to a char array. The lines can be of different
     * lengths. An empty list will return an empty grid.
     * @param lines The list of strings to convert to a grid.
     * @return The grid as a 2D char array.
     */
    public static char[][] of(List<String> lines) {
        Objects.requireNonNull(lines);

        return lines.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    /**
     * Create a square grid from a list of strings. Each string is converted to a char array. The lines can be of
     * different lengths, in which case all lines will be "filled" to the length of the longest lne using the provided
     * fill character. An empty list will return an empty grid.
     * @param lines The list of strings to convert to a grid.
     * @param fill The character to fill the grid with.
     * @return The grid as a 2D char array.
     */
    public static char[][] of(List<String> lines, char fill) {
        Objects.requireNonNull(lines);

        final var maxLen = lines.stream().mapToInt(String::length).max().orElse(0);
        final var grid = new char[lines.size()][maxLen];
        for (var y = 0; y < lines.size(); y++) {
            final var line = lines.get(y);
            Arrays.fill(grid[y], fill);
            System.arraycopy(line.toCharArray(), 0, grid[y], 0, line.length());
        }

        return grid;
    }

    /**
     * Create a grid with the given dimensions and fill it with the provided character.
     * @param minX The minimum x-coordinate
     * @param maxX The maximum x-coordinate
     * @param minY The minimum y-coordinate
     * @param maxY The maximum y-coordinate
     * @param init The character to fill the grid with.
     * @return The grid as a 2D char array.
     */
    public static char[][] of(int minX, int maxX, int minY, int maxY, char init) {
        if (maxX <= minX)
            throw new IllegalArgumentException("maxX must be greater than minX");
        if (maxY <= minY)
            throw new IllegalArgumentException("maxY must be greater than minY");

        final var grid = new char[maxY - minY][maxX - minX];
        for (var row : grid)
            Arrays.fill(row, init);

        return grid;
    }

    /**
     * Create a copy of the provided grid.
     * @param grid The grid to copy, must not be null.
     * @return A copy of the provided grid.
     */
    public static char[][] copy(char[][] grid) {
        Objects.requireNonNull(grid);

        final var copy = new char[grid.length][grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, copy[y], 0, grid[y].length);
        }

        return copy;
    }

    /**
     * Create a copy of the provided rectangular grid, 3 times the original size and fill the additional space with the
     * provided character. The original grid is placed in the center of the new grid.
     * @param grid The grid to copy.
     * @param init The character to fill the additional space with.
     * @return A copy of the provided grid filled with the provided character.
     * @throws IllegalArgumentException if the grid is not rectangular.
     */
    public static char[][] grow(char[][] grid, char init) {
        Objects.requireNonNull(grid);
        if (!isSquare(grid))
            throw new IllegalArgumentException("Grid must be square");

        final int largerSize = grid.length * 3;
        final var largerGrid = new char[largerSize][largerSize];

        for (int blockY = 0; blockY < 3; blockY++)
            for (int blockX = 0; blockX < 3; blockX++) {
                final int offsetY = blockY * grid.length;
                final int offsetX = blockX * grid.length;

                if (blockY == 1 && blockX == 1) {
                    for (int y = 0; y < grid.length; y++)
                        System.arraycopy(grid[y], 0, largerGrid[offsetY + y], offsetX, grid.length);
                } else {
                    for (int y = 0; y < grid.length; y++)
                        for (int x = 0; x < grid.length; x++)
                            Arrays.fill(largerGrid[offsetY + y], offsetX, offsetX + grid.length, init);
                }
            }

        return largerGrid;
    }

    /**
     * Validate that the provided grid is square.
     * @param grid The grid to validate, must not be null.
     * @return True if the grid is square, false otherwise.
     */
    public static boolean isSquare(char[][] grid) {
        Objects.requireNonNull(grid);

        for (var row : grid)
            if (row.length != grid.length)
                return false;

        return true;
    }

    /**
     * Create a copy of the provided grid that is rotated 90 degrees clockwise.
     * @param grid The grid to rotate, must not be null.
     * @return The rotated grid.
     */
    public static char[][] rotate(char[][] grid) {
        Objects.requireNonNull(grid);
        if (!isRectangular(grid))
            throw new IllegalArgumentException("Grid must be rectangular");

        final var size = new Size(grid[0].length, grid.length);
        final var rotated = new char[size.dx()][size.dy()];
        for (var y = 0; y < size.dy(); y++) {
            for (int x = 0;x < size.dx(); x++) {
                rotated[x][size.dy() - y - 1] = grid[y][x];
            }
        }

        return rotated;
    }

    /**
     * Validate that the provided grid is rectangular.
     * @param grid The grid to validate, must not be null.
     * @throws IllegalArgumentException if the grid is not square.
     */
    public static boolean isRectangular(char[][] grid) {
        Objects.requireNonNull(grid);

        for (var row : grid)
            if (row.length != grid[0].length)
                return false;

        return true;
    }

    /**
     * Create a copy of the provided grid that is flipped horizontally (top becomes bottom and bottom becomes top).
     * @param grid The grid to rotate, must not be null.
     * @return The rotated grid.
     */
    public static char[][] flipHorizontally(char[][] grid) {
        final var sizeY = grid.length;
        final var sizeX = grid[0].length;

        final var flipped = new char[sizeY][sizeX];
        for (var y = 0; y < sizeY; y++) {
            System.arraycopy(grid[y], 0, flipped[sizeY - y - 1], 0, sizeX);
        }

        return flipped;
    }

    /**
     * Create a copy of the provided grid that is flipped vertically (left becomes right and right becomes left).
     * @param grid The grid to rotate, must not be null.
     * @return The rotated grid.
     */
    public static char[][] flipVertically(char[][] grid) {
        Objects.requireNonNull(grid);

        final var sizeY = grid.length;
        final var sizeX = grid[0].length;

        final var flipped = new char[sizeY][sizeX];
        for (var y = 0; y < sizeY; y++) {
            for (int x = 0;x < sizeX; x++) {
                flipped[y][sizeX - x - 1] = grid[y][x];
            }
        }

        return flipped;
    }

    /**
     * Compare two grids for equality. Two grids are equal if they have the same dimensions and all elements are equal.
     * @param a The first grid to compare, must not be null.
     * @param b The second grid to compare, must not be null.
     * @return True if the grids are equal, false otherwise.
     */
    public static boolean gridEquals(char[][] a, char[][] b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);

        if (a.length != b.length)
            return false;

        for (var y = 0; y < a.length; y++) {
            if (a[y].length != b[y].length)
                return false;

            for (var x = 0; x < a[y].length; x++)
                if (a[y][x] != b[y][x])
                    return false;
        }

        return true;
    }

    /**
     * Count the number of elements in the grid that match the provided predicate.
     * @param grid The grid to count the elements in, must not be null.
     * @param filter The predicate to test the elements with, must not be null.
     * @return The number of elements that match the predicate.
     */
    public static long count(char[][] grid, Predicate<Integer> filter) {
        Objects.requireNonNull(grid);
        Objects.requireNonNull(filter);

        return Arrays.stream(grid)
                .flatMapToInt(row -> CharBuffer.wrap(row).chars())
                .filter(filter::test)
                .count();
    }

    /**
     * Count the number of elements in the grid that match the provided character.
     * @param grid The grid to count the elements in, must not be null.
     * @param toCount The character to count.
     * @return The number of elements that match the character.
     */
    public static long count(char[][] grid, char toCount) {
        return count(grid, c -> c == toCount);
    }

    /**
     * Print the provided grid to the console.
     * @param grid The grid to print, must not be null.
     */
    public static void print(char[][] grid) {
        Objects.requireNonNull(grid);

        Arrays.stream(grid)
                .sequential()
                .forEach(row -> System.out.println(String.valueOf(row)));
        System.out.println();
    }

    /**
     * Print the provided grid to the console with a marker at the provided point.
     * @param grid The grid to print, must not be null.
     * @param point The point to mark, must not be null.
     * @param marker The character to use as a marker.
     */
    public static void print(char[][] grid, Point point, char marker) {
        Objects.requireNonNull(grid);
        Objects.requireNonNull(point);

        for (var y = 0; y < grid.length; y++) {
            for (var x = 0; x < grid.length; x++) {
                if (y == point.y() && x == point.x())
                    System.out.print(marker);
                else
                    System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    /**
     * Convert the provided grid to a single line string. All grid rows are concatenated.
     * @param grid The grid to convert, must not be null.
     * @return The grid as a string.
     */
    public static String toString(char[][] grid) {
        Objects.requireNonNull(grid);

        return Arrays.stream(grid)
                .sequential()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    /**
     * Convert the provided grid to a list of strings. Each row in the grid is converted to a string.
     * @param grid The grid to convert, must not be null.
     * @return The grid as a list of strings.
     */
    public static List<String> toList(char[][] grid) {
        Objects.requireNonNull(grid);

        return Arrays.stream(grid)
                .sequential()
                .map(String::valueOf)
                .toList();
    }
}
