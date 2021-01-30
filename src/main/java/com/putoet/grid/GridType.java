package com.putoet.grid;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface GridType {
    void set(int x, int y, char c);

    char get(int x, int y);

    boolean contains(int x, int y);

    int minX();

    int maxX();

    int minY();

    int maxY();

    int width();

    int height();

    Optional<Point> findFirst(Predicate<Character> predicate);

    List<Point> findAll(Predicate<Character> predicate);
}
