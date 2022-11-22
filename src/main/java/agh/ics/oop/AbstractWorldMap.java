package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected ArrayList<HashMap<Vector2d, IMapElement>> elements;
    // 0th list: animals:
    // 1st: grass
    public Vector2d lower;
    public Vector2d upper;




    MapVisualizer visualiser;

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        IMapElement obj = this.elements.get(0).get(oldPosition);

        this.elements.get(0).remove(oldPosition);
        this.elements.get(0).put(newPosition, obj);
    }

    public String toString() {
        return this.visualiser.draw(this.lower, this.upper);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !this.elements.get(0).containsKey(position);
    }




    @Override
    public boolean isOccupied(Vector2d position) {
        for(HashMap<Vector2d, IMapElement> map: this.elements){
            if(map.containsKey(position)){
                return true;
            }
        }
        return false;

    }

    @Override
    public Object objectAt(Vector2d position) {
        for(HashMap<Vector2d, IMapElement> map: this.elements){
            if(map.containsKey(position)){
                return map.get(position);
            }
        }
        return null;
    }

    @Override
    public boolean place(Animal animal) {
        {
            if (canMoveTo(animal.getPosition())) {
                this.elements.get(0).put(animal.getPosition(), animal);
                animal.addObserver(this);
                handleMovement(animal);
                return true;
            }
            return false;
        }
    }

    public abstract void handleMovement(Animal a);
}

