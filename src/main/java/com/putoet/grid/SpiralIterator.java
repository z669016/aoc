package com.putoet.grid;

public class SpiralIterator implements GritIterator {
    private final Size size;
    private final Point start;
    private int ring;
    private GritIterator iterator;

    public SpiralIterator(Size size) {
        assert size != null;
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
