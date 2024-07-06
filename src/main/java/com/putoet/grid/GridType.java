package com.putoet.grid;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * A GridType represents a rectangular grid of characters. It provides methods to get and set characters, and to find
 * characters. It should be relatively easy to make it generic for any type of object, but for now it is char based
 */
public interface GridType {

    /**
     * Create a copy of this GridType
     * @return a copy of this GridType
     */
    GridType copy();

    /**
     * Set the character at the given x and y position.
     * @param x the x position
     * @param y the y position
     * @param c the character to set
     * @throws IllegalArgumentException if the x or y position is outside the grid
     */
    void set(int x, int y, char c);

    /**
     * Get the character at the given x and y position.
     * @param x the x position
     * @param y the y position
     * @return the character at the given x and y position
     * @throws IllegalArgumentException if the x or y position is outside the grid
     */
    char get(int x, int y);

    /**
     * Check if the given x and y position is within the grid
     * @param x the x position
     * @param y the y position
     * @return true if the x and y position is within the grid, false otherwise
     */
    boolean contains(int x, int y);

    /**
     * Get the minimum x position of the grid
     * @return the minimum x position of the grid
     */
    int minX();

    /**
     * Get the maximum x position of the grid
     * @return the maximum x position of the grid
     */
    int maxX();

    /**
     * Get the minimum y position of the grid
     * @return the minimum y position of the grid
     */
    int minY();

    /**
     * Get the maximum y position of the grid
     * @return the maximum y position of the grid
     */
    int maxY();

    /**
     * Get the width of the grid
     * @return the width of the grid
     */
    int width();

    /**
     * Get the height of the grid
     * @return the height of the grid
     */
    int height();

    /**
     * Find the first character that matches the given predicate
     * @param predicate the predicate to match, must not be null
     * @return an optional containing the first point that matches the predicate, or an empty optional if no match is found
     */
    Optional<Point> findFirst(Predicate<Character> predicate);

    /**
     * Find all characters that match the given predicate
     * @param predicate the predicate to match, must not be null
     * @return a list of points that match the predicate
     */
    List<Point> findAll(Predicate<Character> predicate);
}
