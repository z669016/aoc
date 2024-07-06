package com.putoet.grid;

/**
 * An iterator over a grid of points.
 */
public interface GritIterator {
    /**
     * Check if there are more points available.
     * @return True if there are more points available, false otherwise.
     */
    boolean hasNext();

    /**
     * Get the next point in the grid.
     * @return The next point in the grid.
     * @throws IllegalStateException if there are no more points available.
     */
    Point next();

    /**
     * Get the starting point of the grid.
     * @return The starting point of the grid.
     */
    Point start();
}
