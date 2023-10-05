package com.putoet.grid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SquareIteratorTest {
    private static final Integer[][] SQUARE = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
    };

    private static final GritIterator ITER = new SquareIterator(new Size(SQUARE.length, SQUARE[0].length));
    private static final GritIterator ITER2 = new SquareIterator(Point.of(1, 1), new Size(SQUARE.length - 2, SQUARE[0].length - 2));
    private static final GritIterator ITER3 = new SquareIterator(Point.of(2, 2), new Size(SQUARE.length - 4, SQUARE[0].length - 4));

    @ParameterizedTest
    @MethodSource("hasNextProvider")
    void hasNext(boolean hasNext, Point point, int value) {
        assertEquals(hasNext, ITER.hasNext());
        if (hasNext) {
            final var next = ITER.next();
            assertEquals(point, next);
            assertEquals(value, SQUARE[next.y()][next.x()]);
        }
    }

    private static Stream<Arguments> hasNextProvider() {
        return Stream.of(
                Arguments.of(true, Point.of(0, 0), 1),
                Arguments.of(true, Point.of(1, 0), 2),
                Arguments.of(true, Point.of(2, 0), 3),
                Arguments.of(true, Point.of(3, 0), 4),
                Arguments.of(true, Point.of(4, 0), 5),
                Arguments.of(true, Point.of(4, 1), 10),
                Arguments.of(true, Point.of(4, 2), 15),
                Arguments.of(true, Point.of(4, 3), 20),
                Arguments.of(true, Point.of(4, 4), 25),
                Arguments.of(true, Point.of(3, 4), 24),
                Arguments.of(true, Point.of(2, 4), 23),
                Arguments.of(true, Point.of(1, 4), 22),
                Arguments.of(true, Point.of(0, 4), 21),
                Arguments.of(true, Point.of(0, 3), 16),
                Arguments.of(true, Point.of(0, 2), 11),
                Arguments.of(true, Point.of(0, 1), 6),
                Arguments.of(false, Point.of(0, 0), 1)
        );
    }

    @ParameterizedTest
    @MethodSource("hasNext2Provider")
    void hasNext2(boolean hasNext, Point point, int value) {
        assertEquals(hasNext, ITER2.hasNext());
        if (hasNext) {
            final var next = ITER2.next();
            assertEquals(point, next);
            assertEquals(value, SQUARE[next.y()][next.x()]);
        }
    }

    private static Stream<Arguments> hasNext2Provider() {
        return Stream.of(
                Arguments.of(true, Point.of(1, 1), 7),
                Arguments.of(true, Point.of(2, 1), 8),
                Arguments.of(true, Point.of(3, 1), 9),
                Arguments.of(true, Point.of(3, 2), 14),
                Arguments.of(true, Point.of(3, 3), 19),
                Arguments.of(true, Point.of(2, 3), 18),
                Arguments.of(true, Point.of(1, 3), 17),
                Arguments.of(true, Point.of(1, 2), 12),
                Arguments.of(false, Point.of(1, 1), 7)
        );
    }

    @Test
    void hasNext3() {
        assertTrue(ITER3.hasNext());
        final var next = ITER3.next();
        assertEquals(Point.of(2, 2), next);
        assertEquals(13, SQUARE[next.y()][next.x()]);
        assertFalse(ITER3.hasNext());

        assertThrows(IllegalStateException.class, ITER3::next);
    }
}