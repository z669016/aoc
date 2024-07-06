package com.putoet.grid;

import java.util.Objects;

/**
 * An iterator over a grid of points following a square. It starts at the origin, assuming that's the top-left of the
 * grid, and traverses around the outer-square of the grid. Unlike the {@link com.putoet.grid.SpiralIterator}, this
 * iterator does not spiral towards the center. This means it will not return all points in the grid, only the ones on
 * the outer-square.
 */
public class SquareIterator implements GritIterator {
    private final Size size;
    private final Point start;
    private Point current;

    /**
     * Create a new SquareIterator for the given size.
     * @param size The size of the grid to iterate over, must not be null.
     */
    public SquareIterator(Size size) {
        Objects.requireNonNull(size);

        this.size = size;
        this.start = Point.of(0, 0);
    }

    /**
     * Create a new SquareIterator for the given size starting at the given point.
     * @param start The starting point of the square, must not be null.
     * @param size The size of the grid to iterate over, must not be null.
     */
    public SquareIterator(Point start, Size size) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(size);

        this.size = size;
        this.start = start;
    }

    @Override
    public boolean hasNext() {
        return current == null || !current.equals(start);
    }

    @Override
    public Point next() {
        if (!hasNext())
            throw new IllegalStateException("No more elements available");

        if (current == null) {
            current = start;
        }

        var next = current;

        var x = current.x();
        var y = current.y();

        // move left to right across the top row
        if (x < start.x() + size.dx() - 1 && y == start.y()) {
            x += 1;
        }

        // move top to bottom along the right column
        else if (x == start.x() + size.dx() - 1 && y < start.y() + size.dy() - 1) {
            y += 1;
        }

        // move right to left along the bottom row
        else if (x > start.x() && y == start.y() + size.dy() - 1) {
            x -= 1;
        }

        // move bottom to top along the left column
        else if (x == start.x() && y > start.y()) {
            y -= 1;
        }
        current = Point.of(x, y);

        return next;
    }

    @Override
    public Point start() {
        return start;
    }
}
