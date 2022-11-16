package agh.ics.oop;

public class Grass implements IMapElement{
    public Vector2d position;
    public Grass(Vector2d pos) {
        this.position = pos;
    }

    public String toString() {
        return "*";
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }


}
