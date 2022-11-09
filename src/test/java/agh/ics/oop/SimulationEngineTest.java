package agh.ics.oop;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    void collisionTest() throws InterruptedException {
        String str = "f b r l f f r r f f f f f f f f";
        String[] args = str.split(" ");
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Animal a1= (Animal) map.objectAt(new Vector2d(2,0));
        Animal a2 = (Animal) map.objectAt(new Vector2d(3,4));


        assertNotNull(a1);
        assertNotNull(a2);
        assertTrue(a1.isFacing(MapDirection.SOUTH));
        assertTrue(a2.isFacing(MapDirection.NORTH));
    }

    @Test
    void generalTest() throws InterruptedException {
        String str = "f f f f f r f f f f r f f r f f f f r f f f f f f r f f f f r f f f f f f f f f";
        String[] args = str.split(" ");
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(4,4);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(0,1)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Animal a1 = (Animal) map.objectAt(new Vector2d(1,0));
        Animal a2 = (Animal) map.objectAt(new Vector2d(0,0));

        assertNotNull(a1);
        assertNotNull(a2);
        assertTrue(a1.isFacing(MapDirection.WEST));
        assertTrue(a2.isFacing(MapDirection.WEST));
    }
}