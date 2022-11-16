package agh.ics.oop;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    void collisionRectangularTest() throws InterruptedException {
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
    void generalRectangularTest() throws InterruptedException {
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
    @Test
    void grassFieldExpansionTest() throws InterruptedException {
        String str = "f f f r f f f f r f f f f f r f f f f f f r f f f f f f f";
        String[] args = str.split(" ");
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(2);
        Vector2d[] positions = {new Vector2d(0,0)};
        IEngine engine = new SimulationEngine(directions,map,positions);
        engine.run();

        Animal a = (Animal) map.objectAt(new Vector2d(-2,5));

        assertNotNull(a);
        assertTrue(a.isFacing(MapDirection.NORTH));

    }

    @Test
    void grassEatingTest() throws InterruptedException {
        String str = "f f f f f f f f";
        String[] args = str.split(" ");
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(1);
        Vector2d[] positions = {new Vector2d(0,0)};
        int grassPlaced = 1;
        Grass[] gList = {new Grass(new Vector2d(0,2)), new Grass(new Vector2d(0,3)), new Grass(new Vector2d(0,4))};
        for(Grass g: gList){
            if(map.placeGrass(g)){
                grassPlaced+=1;
            }
        }
        IEngine engine = new SimulationEngine(directions,map,positions);
        engine.run();

        assertEquals(grassPlaced, map.elementList.get(1).size());
    }
}