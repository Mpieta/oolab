package agh.ics.oop;

import java.util.*;


public class GrassField extends AbstractWorldMap{

    public int initialN;
    private MapBoundary boundaryObserver;
    public ArrayList<Vector2d> generateRandomGrassField(int n){
        int range = (int) Math.round(Math.sqrt(10*n));
        ArrayList<Vector2d> grassPositions = new ArrayList<>();
        ArrayList<Vector2d> pos = new ArrayList<>();
        for(int i = 0;i<range;i++){
            for(int j = 0;j<range;j++){
                pos.add(new Vector2d(i,j));
            }
        }
        Collections.shuffle(pos);

        int cnt = 0;
        for(Vector2d v: pos){
            if(cnt==n){
                break;
            }
            grassPositions.add(v);
            cnt+=1;
        }
        return grassPositions;
    }

    public GrassField(int n) {
        this.elements = new ArrayList<HashMap<Vector2d, IMapElement>>();
        this.elements.add(new HashMap<Vector2d, IMapElement>());
        this.elements.add(new HashMap<Vector2d, IMapElement>());
        this.initialN = n;
        this.boundaryObserver = new MapBoundary();

        ArrayList<Vector2d> grassPositions = this.generateRandomGrassField(n);
        Vector2d tempHigh = new Vector2d(-1*Integer.MAX_VALUE,-1*Integer.MAX_VALUE);
        Vector2d tempLow = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for(Vector2d pos: grassPositions){
            tempLow = tempLow.lowerLeft(pos);
            tempHigh = tempHigh.upperRight(pos);
            Grass g = new Grass(pos);
            this.elements.get(1).put(g.getPosition(), g);
            this.boundaryObserver.addElement(g);
        }
        this.lower = tempLow;
        this.upper = tempHigh;
        this.visualiser = new MapVisualizer(this);

        System.out.println(tempHigh);
    }

    void setNewBoundaries(){
        Vector2d clow = new Vector2d(-1*Integer.MAX_VALUE, -1*Integer.MAX_VALUE);
        Vector2d chigh = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for(HashMap<Vector2d, IMapElement> map: this.elements){
            for(Vector2d el: map.keySet()) {
                clow = clow.upperRight(el);
                chigh = chigh.lowerLeft(el);
            }
        }
        if(clow.follows(chigh)){
            Vector2d temp = clow;
            clow = chigh;
            chigh = temp;
        }
        this.lower = clow;
        this.upper = chigh;
    }

    public Vector2d newGrassPosition(){
        Random rng = new Random();
        int range = (int) Math.sqrt(10*initialN);
        int x =rng.nextInt(range);
        int y =rng.nextInt(range);

        while(isOccupied(new Vector2d(x,y))) {
            x = rng.nextInt(range);
            y = rng.nextInt(range);
        }

        return new Vector2d(x,y);
    }

    public boolean placeGrass(Grass g){
        if(isOccupied(g.getPosition())){
            return false;
        }
        this.initialN+=1;
        this.elements.get(1).put(g.getPosition(), g);
        this.lower = this.lower.lowerLeft(g.getPosition());
        this.upper = this.upper.upperRight(g.getPosition());
        return true;
    }

    public Grass grassAt(Vector2d pos){
        return (Grass) this.elements.get(1).get(pos);
    }

    public boolean place(Animal animal) throws IllegalArgumentException{
        {
            if (canMoveTo(animal.getPosition())) {
                this.elements.get(0).put(animal.getPosition(), animal);
                animal.addObserver(this);
                this.boundaryObserver.addElement(animal);
                animal.addObserver(this.boundaryObserver);
                handleMovement(animal);
                return true;
            }
            throw new IllegalArgumentException(animal.getPosition() + " is not a valid position to place animal(position already occupied by another animal)");
        }
    }

    @Override
    public void handleMovement(Animal a) {
        Vector2d newPos = a.getPosition();

        Grass g = (Grass) this.elements.get(1).get(newPos);
        if(g!=null) {
            this.elements.get(1).remove(newPos);
            Vector2d newGrassPos = newGrassPosition();
            this.boundaryObserver.positionChanged(g.getPosition(), newGrassPos);
            g.position = newGrassPos;

            this.elements.get(1).put(newGrassPos, g);
            this.boundaryObserver.addElement(a); //treeSet nie moze zawierać duplikatów, po kolizji grass z animal
            // metoda positionChanged wywolana na trawie usunie również pozycje zwierzecia
        }
        this.boundaryObserver.setNewBoundaries(this);
    }
}
