package agh.ics.oop;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {





    @Test
    void place() {
        RectangularMap map = new RectangularMap(10,5);


        Vector2d[] positions = {
                new Vector2d(4,2),
                new Vector2d(4,2),
                new Vector2d(2,2),
                new Vector2d(999,999),
                new Vector2d(-1,5),
                new Vector2d(-1,-1),
                new Vector2d(12,4),
                new Vector2d(9,6)
        };

        boolean[] results = {
                true,
                false,
                true,
                false,
                false,
                false,
                false,
                false,

        };

        for(int i = 0;i< positions.length;i++) {
            Animal a = new Animal(map, positions[i]);
            assertEquals(map.place(a), results[i]);
            if(map.place(a)) {
                assertEquals(map.objectAt(positions[i]),a);
            }
        }

    }



    @Test
    void canMoveTo() {
        RectangularMap map = new RectangularMap(10,5);

        Vector2d[] animalPositions = {
                new Vector2d(2,2),
                new Vector2d(4,3),
                new Vector2d(6,4),
                new Vector2d(7,2),
                new Vector2d(9,1)

        };
        for (Vector2d animalPosition : animalPositions) {
            map.place(new Animal(map, animalPosition));
        }

        Vector2d[] testPositions = {
                //out of map
                new Vector2d(10,5),
                new Vector2d(10,4),
                new Vector2d(-1,-1),
                new Vector2d(-1,0),
                new Vector2d(0,-1),
                new Vector2d(10,0),
                new Vector2d(10,-1),
                new Vector2d(9,6),
                new Vector2d(0,6),
                new Vector2d(-1,6),
                //true
                new Vector2d(5,2)
        };

        boolean[] results = {
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                //////////
                true
        };

        for(int i = 0;i<testPositions.length;i++) {
            assertEquals(results[i],map.canMoveTo(testPositions[i]));
        }

    }



    @Test
    void isOccupied() {
        RectangularMap map = new RectangularMap(10,5);

        Vector2d[] animalPositions = {
                new Vector2d(2,2),
                new Vector2d(4,3),
                new Vector2d(6,4),
                new Vector2d(7,2),
                new Vector2d(9,1)

        };
        for (Vector2d animalPosition : animalPositions) {
            map.place(new Animal(map, animalPosition));
        }
        Vector2d[] testPositions = {
                new Vector2d(2, 2),
                new Vector2d(4, 3),
                new Vector2d(6, 4),
                new Vector2d(7, 2),
                new Vector2d(9, 1),
                new Vector2d(0, 0),
                new Vector2d(3, 3)
        };


        boolean[] results = {
                true,
                true,
                true,
                true,
                true,
                false,
                false
        };

        for(int i = 0;i<testPositions.length;i++) {
            assertEquals(results[i],map.isOccupied(testPositions[i]));
        }


    }

    @Test
    void objectAt() {
        RectangularMap map = new RectangularMap(10,5);

        //Animal b = new Animal(map, new Vector2d(5,2));
        Animal a = new Animal(map, new Vector2d(5,2));
        map.place(a);
        assertEquals(a, map.objectAt(new Vector2d(5,2)));
        assertNull(map.objectAt(new Vector2d(0, 0)));





    }
}