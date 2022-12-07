package agh.ics.oop;

import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    TreeSet<Vector2d> xPositions = new TreeSet<>((o1, o2) -> {
        if(o1.x==o2.x){
            return o1.y-o2.y;
        }
        return o1.x -o2.x;
    });
    TreeSet<Vector2d> yPositions = new TreeSet<>((o1, o2) -> {
        if(o1.y==o2.y){
            return o1.x-o2.x;
        }
        return o1.y - o2.y;
    });

    public void addElement(IMapElement el){
        this.xPositions.add(el.getPosition());
        this.yPositions.add(el.getPosition());
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.xPositions.remove(oldPosition);
        this.xPositions.add(newPosition);

        this.yPositions.remove(oldPosition);
        this.yPositions.add(newPosition);
    }

    public Vector2d getLower(){
        return new Vector2d(this.xPositions.first().x, this.yPositions.first().y);
    }

    public Vector2d getUpper(){
        return new Vector2d(this.xPositions.last().x, this.yPositions.last().y);
    }

    public void setNewBoundaries(AbstractWorldMap map){
        map.lower = this.getLower();
        map.upper = this.getUpper();
    }
}
