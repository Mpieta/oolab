package agh.ics.oop;

import java.util.ArrayList;

public class Animal implements IMapElement {
    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;

    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();



    public Animal(){

        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.map = new RectangularMap(5,5);
    }
    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
    }

    void addObserver(IPositionChangeObserver o){
        this.observers.add(o);
    }

    void removeObserver(IPositionChangeObserver o){
        this.observers.remove(o);
    }

    void reportPositionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver o: this.observers){
            o.positionChanged(oldPosition, newPosition);
        }
    }
    public String toString(){
        return this.position.toString();
    }

    public boolean isAt(Vector2d pos) {
        return this.position.equals(pos);
    }

    public boolean isFacing(MapDirection m) {
        return this.orientation.equals(m);
    }

    public void move(MoveDirection direction) {

        switch (direction) {
            case RIGHT: {
                this.orientation = this.orientation.next();
                break;
            }
            case LEFT: {
                this.orientation = this.orientation.previous();
                break;
            }
            case FORWARD: {
                Vector2d npos = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(npos)) {
                    reportPositionChanged(this.position, npos);
                    this.position = npos;
                }
                break;
            }
            case BACKWARD: {
                Vector2d npos = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(npos)) {
                    reportPositionChanged(this.position, npos);
                    this.position = npos;
                }
                break;
            }
            default:
                break;
        }
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String getImage() {
        switch(this.orientation){
            case NORTH -> {
                return "src/main/resources/up.png";
            }
            case SOUTH -> {
                return "src/main/resources/down.png";
            }
            case WEST -> {
                return "src/main/resources/left.png";
            }
            case EAST -> {
                return "src/main/resources/right.png";
            }
            default -> {
                return null;
            }

        }
    }
}
