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
    void of() {
        final String s = GridUtils.toString(NUMBERS);
        assertEquals("123456789", s);
    }

    @Test
    void grow() {
        char[][] grid = GridUtils.grow(NUMBERS, '.');

        final List<String> list = GridUtils.toList(grid);
        final List<String> expected = List.of(
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
    void rotate() {
        final char[][] rotated = GridUtils.rotate(NUMBERS);
        GridUtils.print(rotated);

        assertTrue(GridUtils.gridEquals(rotated, ROTATED));
    }

    @Test
    void flipHorizontally() {
        final char[][] flipped = GridUtils.of(List.of(
                "789",
                "456",
                "123"
        ));
        assertTrue(GridUtils.gridEquals(flipped, GridUtils.flipHorizontally(NUMBERS)));
    }

    @Test
    void flipVertically() {
        final char[][] flipped = GridUtils.of(List.of(
                "321",
                "654",
                "987"
        ));
        assertTrue(GridUtils.gridEquals(flipped, GridUtils.flipVertically(NUMBERS)));
    }
}