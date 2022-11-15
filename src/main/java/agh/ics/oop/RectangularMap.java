package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap {


    public RectangularMap(int width, int height) {
        this.elementList = new ArrayList<>();
        this.lower = new Vector2d(0,0);
        this.upper = new Vector2d(width-1, height-1);

        this.elementList.add(new ArrayList<IMapElement>());
        this.visualiser = new MapVisualizer(this);

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(!position.follows(this.lower) || !position.precedes(this.upper)) {
            System.out.println(position);
            return false;
        }
        for (IMapElement a : elementList.get(0)) {
            if (a.isAt(position)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        {
            if (canMoveTo(animal.getPosition())) {
                this.elementList.get(0).add(animal);
                return true;
            }
            return false;
        }
    }
}
