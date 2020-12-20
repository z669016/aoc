package com.putoet.grid;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void contains() {
        final Grid grid = new Grid(-7, 9, -13, 17,
                GridUtils.of(-7, 9, -13, 17, '.'));

        assertTrue(grid.contains(-7, -13));
        assertFalse(grid.contains(-7, -14));
        assertFalse(grid.contains(-8, -14));
        assertFalse(grid.contains(-8, -13));

        assertTrue(grid.contains(8, -13));
        assertFalse(grid.contains(8, -14));
        assertFalse(grid.contains(9, -14));
        assertFalse(grid.contains(9, -13));

        assertTrue(grid.contains(8, 16));
        assertFalse(grid.contains(9, 16));
        assertFalse(grid.contains(9, 17));
        assertFalse(grid.contains(8, 17));

        assertTrue(grid.contains(-7, 16));
        assertFalse(grid.contains(-7, 17));
        assertFalse(grid.contains(-8, 17));
        assertFalse(grid.contains(-8, 16));
    }

    @Test
    void rotate() {
        final Grid grid = new Grid(GridUtils.of(List.of(
                "1234",
                "5678",
                "90ab"
        )));

        final char[][] expected = GridUtils.of(List.of(
                "951",
                "062",
                "a73",
                "b84"
        ));

        assertEquals(0, grid.minX());
        assertEquals(0, grid.minY());
        assertEquals(4, grid.maxX());
        assertEquals(3, grid.maxY());

        final Grid rotated = grid.rotate();
        assertTrue(GridUtils.gridEquals(expected, rotated.grid()));
        assertEquals(0, rotated.minX());
        assertEquals(0, rotated.minY());
        assertEquals(3, rotated.maxX());
        assertEquals(4, rotated.maxY());
    }

    @Test
    void flipHorizontally() {
        final Grid grid = new Grid(GridUtils.of(List.of(
                "1234",
                "5678",
                "90ab"
        )));

        final char[][] expected = GridUtils.of(List.of(
                "90ab",
                "5678",
                "1234"
        ));

        assertEquals(0, grid.minX());
        assertEquals(0, grid.minY());
        assertEquals(4, grid.maxX());
        assertEquals(3, grid.maxY());

        final Grid flipped = grid.flipHorizontally();

        assertTrue(GridUtils.gridEquals(expected, flipped.grid()));
        assertEquals(0, flipped.minX());
        assertEquals(0, flipped.minY());
        assertEquals(4, flipped.maxX());
        assertEquals(3, flipped.maxY());
    }

    @Test
    void flipVertically() {
        final Grid grid = new Grid(GridUtils.of(List.of(
                "1234",
                "5678",
                "90ab"
        )));

        final char[][] expected = GridUtils.of(List.of(
                "4321",
                "8765",
                "ba09"
        ));

        assertEquals(0, grid.minX());
        assertEquals(0, grid.minY());
        assertEquals(4, grid.maxX());
        assertEquals(3, grid.maxY());

        final Grid flipped = grid.flipVertically();

        assertTrue(GridUtils.gridEquals(expected, flipped.grid()));
        assertEquals(0, flipped.minX());
        assertEquals(0, flipped.minY());
        assertEquals(4, flipped.maxX());
        assertEquals(3, flipped.maxY());
    }
}