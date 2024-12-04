package com.putoet.words;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoundWordTest {

    @Test
    void foundLetters() {
        final var word = new FoundWord("WORD", Point.of(3,1), Point.NORTH, 3, 4);
        final var expected = List.of(
                new FoundLetter('W', Point.of(3, 1)),
                new FoundLetter('O', Point.of(3, 2)),
                new FoundLetter('R', Point.of(3, 3)),
                new FoundLetter('D', Point.of(3, 4))
        );

        assertEquals(expected, word.foundLetters());
    }
}