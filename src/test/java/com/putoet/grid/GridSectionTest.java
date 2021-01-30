package com.putoet.grid;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GridSectionTest {
    private Grid grid;
    private GridSection[] sections;

    @BeforeEach
    void setup() {
        final List<String> lines = ResourceLines.list("/gridsection.txt");
        grid = new Grid(GridUtils.of(lines));

        sections = new GridSection[] {
                new GridSection(grid, Point.of(0, 0), Point.of(5, 3)),
                new GridSection(grid, Point.of(4, 0), Point.of(9, 3)),
                new GridSection(grid, Point.of(4, 2), Point.of(9, 5)),
                new GridSection(grid, Point.of(0, 2), Point.of(5, 5))
        };
    }

    @Test
    void minX() {
        assertEquals(0, sections[0].minX());
        assertEquals(4, sections[1].minX());
        assertEquals(4, sections[2].minX());
        assertEquals(0, sections[3].minX());
    }

    @Test
    void maxX() {
        assertEquals(5, sections[0].maxX());
        assertEquals(9, sections[1].maxX());
        assertEquals(9, sections[2].maxX());
        assertEquals(5, sections[3].maxX());
    }

    @Test
    void minY() {
        assertEquals(0, sections[0].minY());
        assertEquals(0, sections[1].minY());
        assertEquals(2, sections[2].minY());
        assertEquals(2, sections[3].minY());
    }

    @Test
    void maxY() {
        assertEquals(3, sections[0].maxY());
        assertEquals(3, sections[1].maxY());
        assertEquals(5, sections[2].maxY());
        assertEquals(5, sections[3].maxY());
    }

    @Test
    void width() {
        assertEquals(5, sections[0].width());
        assertEquals(5, sections[1].width());
        assertEquals(5, sections[2].width());
        assertEquals(5, sections[3].width());
    }

    @Test
    void height() {
        assertEquals(3, sections[0].height());
        assertEquals(3, sections[1].height());
        assertEquals(3, sections[2].height());
        assertEquals(3, sections[3].height());
    }

    @Test
    void set() {
        assertThrows(AssertionError.class, () -> sections[0].set(0, 3, '$'));
        assertThrows(AssertionError.class, () -> sections[1].set(0, 2, '$'));
        assertThrows(AssertionError.class, () -> sections[2].set(3, 3, '$'));
        assertThrows(AssertionError.class, () -> sections[3].set(5, 3, '$'));

        sections[0].set(0, 0, '$');
        assertEquals('$', grid.get(0, 0));
        assertEquals('$', sections[0].get(0, 0));

        sections[1].set(8, 0, '$');
        assertEquals('$', grid.get(8, 0));
        assertEquals('$', sections[1].get(8, 0));

        sections[2].set(8, 4, '$');
        assertEquals('$', grid.get(8, 4));
        assertEquals('$', sections[2].get(8, 4));

        sections[3].set(0, 4, '$');
        assertEquals('$', grid.get(0, 4));
        assertEquals('$', sections[3].get(0, 4));
    }

    @Test
    void findFirst() {
        Optional<Point> point = sections[0].findFirst(c -> c == 'A');
        assertTrue(point.isPresent());
        assertEquals(Point.of(1, 1), point.get());

        point = sections[1].findFirst(c -> c == 'B');
        assertTrue(point.isPresent());
        assertEquals(Point.of(7, 1), point.get());

        point = sections[2].findFirst(c -> c == 'C');
        assertTrue(point.isPresent());
        assertEquals(Point.of(7, 3), point.get());

        point = sections[3].findFirst(c -> c == 'D');
        assertTrue(point.isPresent());
        assertEquals(Point.of(1, 3), point.get());
    }

    @Test
    void findAll() {
        final List<Point> points = sections[0].findAll(c -> c == '.');
        assertEquals(13, points.size());
    }

    @Test
    void print() {
        System.out.println(grid);

        for (int i = 0; i < sections.length; i++) {
            System.out.println("Section: " + i);
            System.out.println(sections[i]);
        }
    }
}