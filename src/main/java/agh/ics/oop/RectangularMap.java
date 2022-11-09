package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    private int width;
    private int height;
    public ArrayList<Animal> animalList;
    private MapVisualizer visualiser;


    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;

        this.animalList = new ArrayList<Animal>();
        this.visualiser = new MapVisualizer(this);

    }

    @Override
    public String toString() {
        return visualiser.draw(new Vector2d(0,0), new Vector2d(width-1, height-1));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(new Vector2d(this.width-1, this.height-1)) && position.follows(new Vector2d(0, 0)) && !this.isOccupied(position);
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
