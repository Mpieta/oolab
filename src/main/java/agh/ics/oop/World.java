package agh.ics.oop;


public class World {

    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(1);
        Vector2d[] positions = { new Vector2d(0,0)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();


    }
}
