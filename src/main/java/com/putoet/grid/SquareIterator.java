package com.putoet.grid;

public class SquareIterator implements GritIterator {
    private final Size size;
    private final Point start;
    private Point current;

    public SquareIterator(Size size) {
        this.size = size;
        this.start = Point.of(0, 0);
    }

    public SquareIterator(Point start, Size size) {
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
