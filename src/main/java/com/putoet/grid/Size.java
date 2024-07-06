package com.putoet.grid;

/**
 * A simple record to represent a size in two dimensions. Both dimensions must be positive.
 */
public record Size(int dx, int dy)  {
    public Size {
        assert dx > 0 && dy > 0;
    }

    public long count() {
        return Math.abs((long) dx * dy);
    }

    @Override
    public String toString() {
        return dx + "x" + dy;
    }
}
