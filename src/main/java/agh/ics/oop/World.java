package agh.ics.oop;


public class World {

    public static void main(String[] args) throws InterruptedException {
        /*MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        //System.out.println(map);*/

        GrassField map1 = new GrassField(50);
        map1.place(new Animal(map1, new Vector2d(0,0)));
        System.out.print(map1);


    }
}
