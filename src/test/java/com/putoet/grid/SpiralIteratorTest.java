package com.putoet.grid;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SpiralIteratorTest {

    @ParameterizedTest
    @MethodSource("iteratorProvider")
    <T> void iterator(List<T> expected, T[][] matrix) {
        final var iterator = new SpiralIterator(new Size(matrix[0].length, matrix.length));
        final var list = new ArrayList<T>();
        while (iterator.hasNext()) {
            final var next = iterator.next();
            final var value = matrix[next.y()][next.x()];

            list.add(value);
        }
        assertEquals(expected, list);
    }

    private static Stream<Arguments> iteratorProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4),
                        new Integer[][]{
                                {1, 2},
                                {4, 3}
                        }),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        new Integer[][]{
                                {1, 2},
                                {6, 3},
                                {5, 4}
                        }),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                        new Integer[][]{
                                {1, 2, 3},
                                {8, 9, 4},
                                {7,  6, 5}
                        }),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16),
                        new Integer[][]{
                                {1, 2, 3, 4},
                                {12, 13, 14, 5},
                                {11, 16, 15, 6},
                                {10, 9, 8, 7}
                        }),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
                        new Integer[][]{
                                {1, 2, 3, 4},
                                {10, 11, 12, 5},
                                {9, 8, 7, 6}
                        }),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25),
                        new Integer[][]{
                                {1, 2, 3, 4, 5},
                                {16, 17, 18, 19, 6},
                                {15, 24, 25, 20, 7},
                                {14, 23, 22, 21, 8},
                                {13, 12, 11, 10, 9}
                        }),
                Arguments.of(
                        List.of("AB", "BC", "CD", "DE", "EF", "FG", "GH", "HI", "IJ", "JK", "KL", "LM"),
                        new String[][] {
                                {"AB", "BC", "CD", "DE"},
                                {"JK", "KL", "LM", "EF"},
                                {"IJ", "HI", "GH", "FG"}
                        })
        );
    }
}