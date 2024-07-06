package com.putoet.grid;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridUtilsTest {
    private static final char[][] NUMBERS = GridUtils.of(List.of(
            "123",
            "456",
            "789"
    ));

    private static final char[][] ROTATED = GridUtils.of(List.of(
            "741",
            "852",
            "963"
    ));

    @Test
    void ofFill() {
        final var grid = GridUtils.of(List.of("1234", "56", "7", ""), '.');
        final var list = GridUtils.toList(grid);
        final var expected = List.of(
                "1234",
                "56..",
                "7...",
                "...."
        );
        assertEquals(expected, list);
    }

    @Test
    void copy() {
        final var copy = GridUtils.copy(NUMBERS);
        assertNotSame(NUMBERS, copy);
        assertTrue(GridUtils.gridEquals(copy, NUMBERS));
    }

    @Test
    void string() {
        final var s = GridUtils.toString(NUMBERS);
        assertEquals("123456789", s);
    }

    @Test
    void growNotSquare() {
        assertThrows(IllegalArgumentException.class, () -> GridUtils.grow(new char[3][2], '.'));
    }

    @Test
    void grow() {
        var grid = GridUtils.grow(NUMBERS, '.');

        final var list = GridUtils.toList(grid);
        final var expected = List.of(
                ".........",
                ".........",
                ".........",
                "...123...",
                "...456...",
                "...789...",
                ".........",
                ".........",
                "........."
        );
        assertEquals(expected, list);
    }

    @Test
    void rotateNotRectangular() {
        assertThrows(IllegalArgumentException.class, () -> GridUtils.rotate(GridUtils.of(List.of("1234", "56", "7", ""))));
    }

    @Test
    void rotate() {
        final var rotated = GridUtils.rotate(NUMBERS);
        GridUtils.print(rotated);

        assertTrue(GridUtils.gridEquals(rotated, ROTATED));
    }

    @Test
    void flipHorizontally() {
        final var flipped = GridUtils.of(List.of(
                "789",
                "456",
                "123"
        ));
        assertTrue(GridUtils.gridEquals(flipped, GridUtils.flipHorizontally(NUMBERS)));
    }

    @Test
    void flipVertically() {
        final var flipped = GridUtils.of(List.of(
                "321",
                "654",
                "987"
        ));
        assertTrue(GridUtils.gridEquals(flipped, GridUtils.flipVertically(NUMBERS)));
    }
}