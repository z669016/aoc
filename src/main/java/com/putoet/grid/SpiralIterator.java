package com.putoet.grid;

import java.util.Objects;

/**
 * An iterator over a grid of points following a spiral. It starts at the origin, assuming that's the top-left of the
 * grid, and spirals clock-wise to the center of the grid. This iterator will return all points in the grid.
 * Under the hood, it will use a {@link com.putoet.grid.SquareIterator} to traverse the outer-square of the grid, and
 * move inwards once the square is traversed.
 */
public class SpiralIterator implements GritIterator {
    private final Size size;
    private final Point start;
    private int ring;
    private GritIterator iterator;

    /**
     * Create a new SpiralIterator for the given size.
     * @param size The size of the grid to iterate over, must be at least 2x2.
     */
    public SpiralIterator(Size size) {
        Objects.requireNonNull(size);

        assert size.dx() > 1;
        assert size.dy() > 1;

        this.size = size;
        this.start = Point.of(0, 0);
        this.ring = 0;
        this.iterator = new SquareIterator(start, size);
    }

    @Override
    public boolean hasNext() {
        return ring < (size.dx() + 1) / 2 - 1 || iterator.hasNext();
    }

    @Override
    public Point next() {
        if (!hasNext())
            throw new IllegalStateException("No more elements available");

        if (iterator.hasNext())
            return iterator.next();

        ring++;
        iterator = new SquareIterator(Point.of(ring, ring), new Size(size.dx() - 2 * ring, size.dy() - 2 * ring));
        return iterator.next();
    }

    @Override
    public Point start() {
        return start;
    }
}
