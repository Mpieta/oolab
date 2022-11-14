package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    private Vector2d lower;
    private Vector2d upper;
    public ArrayList<Animal> animalList;
    private MapVisualizer visualiser;


    public RectangularMap(int width, int height) {
        this.lower = new Vector2d(0,0);
        this.upper = new Vector2d(width-1, height-1);

        this.animalList = new ArrayList<Animal>();
        this.visualiser = new MapVisualizer(this);

    }

    @Override
    public String toString() {
        return visualiser.draw(lower, upper);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(upper) && position.follows(lower) && !this.isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(this.canMoveTo(animal.getPosition())) {
            this.animalList.add(animal);
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal a: this.animalList) {
            if(a.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal a: this.animalList) {
            if(a.getPosition().equals(position)) {
                return a;
            }
        }
        return null;
    }
}
