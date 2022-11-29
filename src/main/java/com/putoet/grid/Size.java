package com.putoet.grid;

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
