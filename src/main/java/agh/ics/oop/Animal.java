package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);


    public String toString(){
        return position.toString() + ";" + orientation.toString();
    }

    public boolean isAt(Vector2d pos) {
        return this.position.equals(pos);
    }

    public boolean isFacing(MapDirection m) {
        return this.orientation.equals(m);
    }

    public void move(MoveDirection direction) {

        if(direction == MoveDirection.RIGHT) {
            this.orientation = this.orientation.next();
        }
        else if(direction == MoveDirection.LEFT) {
            this.orientation = this.orientation.previous();
        }
        else if(direction == MoveDirection.FORWARD) {
            Vector2d npos = this.position.add(this.orientation.toUnitVector());
            if(npos.follows(new Vector2d(0,0)) && npos.precedes(new Vector2d(4,4))) {
                this.position = npos;
            }
        }
        else if(direction == MoveDirection.BACKWARD) {
            Vector2d npos = this.position.subtract(this.orientation.toUnitVector());
            if(npos.follows(new Vector2d(0,0)) && npos.precedes(new Vector2d(4,4))) {
                this.position = npos;
            }
        }
    }


}
