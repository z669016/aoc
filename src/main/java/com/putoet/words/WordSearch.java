package com.putoet.words;

import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;

import java.util.*;
import java.util.stream.Collectors;

public class WordSearch {
    private final List<Point> directions;
    private final boolean wrap;

    public WordSearch() {
        this.directions = List.of(Point.EAST);
        this.wrap = false;
    }

    public WordSearch(List<Point> directions) {
        this.directions = List.copyOf(directions);
        this.wrap = false;
    }

    public WordSearch(List<Point> directions, boolean wrap) {
        this.directions = List.copyOf(directions);
        this.wrap = wrap;
    }

    public Set<FoundWord> words(Set<String> words, List<String> text) {
        assert words != null;
        assert text != null;

        final var grid = GridUtils.of(text);
        final var foundWords = directions.stream()
                .map(direction -> words(words, grid, direction))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        return filter(foundWords);
    }

    private Set<FoundWord> words(Set<String> words, char[][] text, Point direction) {
        final var foundWords = new HashSet<FoundWord>();
        for (var word : words) {
            for (int y = 0; y < text.length; y++) {
                for (int x = 0; x < text[y].length; x++) {
                    final var start = Point.of(x, y);
                    if (containsWord(word, text, start, direction))
                        foundWords.add(new FoundWord(word, start, direction, text[y].length - 1, text.length - 1));
                }
            }
        }

        return foundWords;
    }

    public Set<FoundLetter> letters(Set<String> words, List<String> text) {
        assert words != null;
        assert text != null;

        final var foundWords = words(words, text);

        return foundWords.stream()
                .map(FoundWord::foundLetters)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private boolean containsWord(String word, char[][] text, Point start, Point direction) {
        var point = start;
        for (int offset = 0; offset < word.length(); offset++) {
            if (!wrap)
                if (point.y() < 0 || point.y() >= text.length || point.x() < 0 || point.x() >= text[point.y()].length)
                    return false;

            if (point.y() < 0)
                point = Point.of(point.x(), text.length - 1);

            if (point.y() >= text.length)
                point = Point.of(point.x(), 0);

            if (point.x() < 0)
                point = Point.of(text[point.y()].length - 1, point.y());

            if (point.x() >= text[point.y()].length)
                point = Point.of(0, point.y());

            if (word.charAt(offset) != text[point.y()][point.x()])
                return false;

            point = point.add(direction);
        }

        return true;
    }


    private Set<FoundWord> filter(Set<FoundWord> foundWords) {
        if (foundWords.isEmpty())
            return foundWords;

        final var ordered = new TreeSet<>(
                Comparator.comparing(FoundWord::start)
                        .thenComparing(FoundWord::direction)
                        .thenComparing(FoundWord::word)
        );
        ordered.addAll(foundWords);
        foundWords = new HashSet<>();

        var current = ordered.getFirst();
        for (var next : ordered) {
            if (next.equals(current))
                continue;

            if (current.start().equals(next.start()) && current.direction().equals(next.direction())) {
                if (!next.word().startsWith(current.word())) {
                    foundWords.add(current);
                }
            } else {
                foundWords.add(current);
            }
            current = next;
        }

        if (current != null)
            foundWords.add(current);

        return foundWords;
    }
}
