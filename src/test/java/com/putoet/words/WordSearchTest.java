package com.putoet.words;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordSearchTest {

    @Test
    void words() {
        final var lines = """
                AWAKEN THE POWE ADORNED WITH THE FLAMES BRIGHT IRE
                THE FLAME SHIELDED THE HEART OF THE KINGS
                POWE PO WER P OWE R
                THERE IS THE END
                """
                .lines()
                .toList();
        final var words = Set.of("THE","OWE","MES","ROD","HER");
        final var search = new WordSearch();
        final var found = search.words(words, lines);

        assertEquals(12, found.size());
    }

    @Test
    void letters() {
        final var lines = """
                AWAKEN THE POWE ADORNED WITH THE FLAMES BRIGHT IRE
                THE FLAME SHIELDED THE HEART OF THE KINGS
                POWE PO WER P OWE R
                THERE IS THE END
                QAQAQ
                """
                .lines()
                .toList();
        final var words = Set.of("THE","OWE","MES","ROD","HER","QAQ");
        final var search = new WordSearch(List.of(Point.EAST, Point.WEST));
        final var found = search.letters(words, lines);
        assertEquals(42, found.size());
    }

    @Test
    void wordsWrapped() {
        final var lines = """
                HELWORLT
                ENIGWDXL
                TRODEOAL
                """
                .lines()
                .toList();
        final var words = Set.of("THE","OWE","MES","ROD","RODEO");
        var search = new WordSearch(List.of(Point.EAST, Point.WEST), true);
        var found = search.words(words, lines);

        assertEquals(2, found.size());
    }
}