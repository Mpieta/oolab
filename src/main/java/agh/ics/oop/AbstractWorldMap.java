package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {
    public Vector2d lower;
    public Vector2d upper;

    protected ArrayList<ArrayList<IMapElement>> elementList; //list of lists of sorted in terms of visualising priority of an object type
    // so far: animalList ----> grassList
    MapVisualizer visualiser;

    public String toString() {
        return this.visualiser.draw(this.lower, this.upper);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        for (IMapElement a : elementList.get(0)) {
            if (a.isAt(position)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public abstract boolean place(Animal animal);

    @Override
    public boolean isOccupied(Vector2d position) {
        for (ArrayList<IMapElement> elList : this.elementList) {
            for (IMapElement el : elList) {
                if (el.isAt(position)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (ArrayList<IMapElement> elList : this.elementList) {
            for (IMapElement el : elList) {
                if (el.isAt(position)) {
                    return el;
                }
            }
        }
        return null;
    }
}

