package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {

    @Test
    void testToString() {
        assertEquals(MapDirection.EAST.toString(), "Wschod");
        assertEquals(MapDirection.NORTH.toString(), "Polnoc");
        assertEquals(MapDirection.SOUTH.toString(), "Poludnie");
        assertEquals(MapDirection.WEST.toString(), "Zachod");
    }

    @Test
    void next() {
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
        assertEquals(MapDirection.UNKNOWN, MapDirection.UNKNOWN.next());
    }

    @Test
    void previous() {
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals(MapDirection.UNKNOWN, MapDirection.UNKNOWN.previous());
    }

    @Test
    void toUnitVector() {
        assertEquals(new Vector2d(0,1), MapDirection.NORTH.toUnitVector());
        assertEquals(new Vector2d(1,0), MapDirection.EAST.toUnitVector());
        assertEquals(new Vector2d(0,-1), MapDirection.SOUTH.toUnitVector());
        assertEquals(new Vector2d(-1,0), MapDirection.WEST.toUnitVector());
        assertEquals(new Vector2d(0,0), MapDirection.UNKNOWN.toUnitVector());
    }
}