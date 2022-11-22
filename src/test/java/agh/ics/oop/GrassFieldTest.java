package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void grassFieldExpansionTest() throws InterruptedException {
        String str = "f f f r f f f f r f f f f f r f f f f f f r f f f f f f f";
        String[] args = str.split(" ");
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(3);
        Vector2d[] positions = {new Vector2d(0,0)};
        IEngine engine = new SimulationEngine(directions,map,positions);
        engine.run();

        Animal a = (Animal) map.objectAt(new Vector2d(-2,5));

        assertNotNull(a);
        assertTrue(a.isFacing(MapDirection.NORTH));

    }

    @Test
    void grassTest() throws InterruptedException {
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

        assertEquals(grassPlaced, map.elements.get(1).size());
    }
}