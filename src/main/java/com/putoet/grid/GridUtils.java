package com.putoet.grid;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GridUtils {
    public static char[][] of(List<String> lines) {
        assert lines != null;

        return lines.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static char[][] of(List<String> lines, char fill) {
        assert lines != null;

        final var maxLen = lines.stream().mapToInt(String::length).max();
        final var grid = new char[lines.size()][maxLen.orElse(0)];
        for (int y = 0; y < lines.size(); y++) {
            final var line = lines.get(y);
            Arrays.fill(grid[y], fill);
            for (int x = 0; x < line.length(); x++)
                grid[y][x] = line.charAt(x);
        }
        return grid;
    }

    public static char[][] of(int minX, int maxX, int minY, int maxY, char init) {
        assert maxX > minX && maxY > minY;

        final var grid = new char[maxY - minY][maxX - minX];
        for (var row : grid)
            Arrays.fill(row, init);

        return grid;
    }

    public static char[][] copy(char[][] grid) {
        final var copy = new char[grid.length][grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, copy[y], 0, grid[y].length);
        }

        return copy;
    }

    public static char[][] grow(char[][] grid, char init) {
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
                    // largerGrid[offsetY + y][offsetX + x] = init;
                }
            }

        return largerGrid;
    }

    public static char[][] rotate(char[][] grid) {
        final int sizeY = grid.length;
        final int sizeX = grid[0].length;

        final var rotated = new char[sizeX][sizeY];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0;x < sizeX; x++) {
                rotated[x][sizeY - y - 1] = grid[y][x];
            }
        }

        return rotated;
    }

    public static char[][] flipHorizontally(char[][] grid) {
        final int sizeY = grid.length;
        final int sizeX = grid[0].length;

        final var flipped = new char[sizeY][sizeX];
        for (int y = 0; y < sizeY; y++) {
            System.arraycopy(grid[y], 0, flipped[sizeY - y - 1], 0, sizeX);
        }

        return flipped;
    }

    public static char[][] flipVertically(char[][] grid) {
        final int sizeY = grid.length;
        final int sizeX = grid[0].length;

        final var flipped = new char[sizeY][sizeX];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0;x < sizeX; x++) {
                flipped[y][sizeX - x - 1] = grid[y][x];
            }
        }

        return flipped;
    }

    public static boolean gridEquals(char[][] a, char[][] b) {
        if (a.length != b.length)
            return false;

        for (int y = 0; y < a.length; y++) {
            if (a[y].length != b[y].length)
                return false;

            for (int x = 0; x < a[y].length; x++)
                if (a[y][x] != b[y][x])
                    return false;
        }

        return true;
    }

    public static long count(char[][] grid, Predicate<Integer> filter) {
        return Arrays.stream(grid)
                .flatMapToInt(row -> CharBuffer.wrap(row).chars())
                .filter(filter::test)
                .count();
    }

    public static long count(char[][] grid, char toCount) {
        return count(grid, c -> c == toCount);
    }

    public static void print(char[][] grid) {
        Arrays.stream(grid)
                .sequential()
                .forEach(row -> System.out.println(String.valueOf(row)));
        System.out.println();
    }

    public static void print(char[][] grid, Point point, char marker) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (y == point.y() && x == point.x())
                    System.out.print(marker);
                else
                    System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    public static String toString(char[][] grid) {
        return Arrays.stream(grid)
                .sequential()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static List<String> toList(char[][] grid) {
        return Arrays.stream(grid)
                .sequential()
                .map(String::valueOf)
                .toList();
    }
}
