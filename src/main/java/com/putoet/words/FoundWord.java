package com.putoet.words;

import com.putoet.grid.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record FoundWord(String word, Point start, Point direction, int maxX, int maxY) {
    public FoundWord {
        assert word != null;
        assert start != null;
        assert direction != null;
    }

    List<FoundLetter> foundLetters() {
        var point = start;
        var foundLetters = new ArrayList<FoundLetter>();

        for (char letter : word.toCharArray()) {
            foundLetters.add(new FoundLetter(letter, point));
            point = point.add(direction);

            if (point.x() < 0)
                point = Point.of(maxX, point.y());
            if (point.x() > maxX)
                point = Point.of(0, point.y());
            if (point.y() < 0)
                point = Point.of(point.x(), maxY);
            if (point.y() > maxY)
                point = Point.of(point.x(), 0);
        }

        return Collections.unmodifiableList(foundLetters);
    }
}
