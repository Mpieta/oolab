package agh.ics.oop;

public class Animal implements IMapElement {
    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;

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

    public String toString(){
        return this.orientation.toString();
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
                    this.position = npos;

                }
                break;
            }
            case BACKWARD: {
                Vector2d npos = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(npos)) {
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


}
