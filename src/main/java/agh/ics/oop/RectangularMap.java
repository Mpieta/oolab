package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

public class RectangularMap extends AbstractWorldMap {


    public RectangularMap(int width, int height) {
        this.elements = new ArrayList<>();
        this.elements.add(new HashMap<Vector2d, IMapElement>());

        this.lower = new Vector2d(0,0);
        this.upper = new Vector2d(width-1, height-1);


        this.visualiser = new MapVisualizer(this);

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(!position.follows(this.lower) || !position.precedes(this.upper)) {
            System.out.println(position);
            return false;
        }
        return !this.elements.get(0).containsKey(position);
    }

    @Override
    public void handleMovement(Animal a) {
        return;
    }
}
