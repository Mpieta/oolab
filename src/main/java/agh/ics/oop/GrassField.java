package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;


public class GrassField extends AbstractWorldMap{

    private int initialN;
    public ArrayList<Vector2d> generateRandomGrassField(int n){
        int range = (int) Math.round(Math.sqrt(10*n));
        int current_num = 0;
        int grass_left = n;
        int fields_left = range*range+1;
        ArrayList<Vector2d> grassPositions = new ArrayList<Vector2d>();
        for(int i = 0;i<range;i++){
            if(fields_left <= 0||grass_left==0){
                break;
            }
            for(int j = 0;j<range;j++){
                if(fields_left <= 0 || grass_left==0){
                    break;
                }
                fields_left -=1;
                grass_left = n-current_num;
                Random rng = new Random();

                int rnd = Math.abs(rng.nextInt(range));
                if(rnd%fields_left < grass_left) {
                    current_num +=1;
                    Vector2d p = new Vector2d(i,j);
                    grassPositions.add(p);
                    System.out.println(p);

                }
            }
        }

        return grassPositions;

    }

    void setNewBoundaries(){
        Vector2d clow = new Vector2d(-1*Integer.MAX_VALUE, -1*Integer.MAX_VALUE);
        Vector2d chigh = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for(ArrayList<IMapElement> elList: this.elementList){
            for(IMapElement el: elList) {
                clow = clow.upperRight(el.getPosition());
                chigh = chigh.lowerLeft(el.getPosition());
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
    public GrassField(int n) {
        this.elementList = new ArrayList<>();
        this.elementList.add(new ArrayList<IMapElement>());
        this.elementList.add(new ArrayList<IMapElement>());
        this.initialN = n;

        ArrayList<Vector2d> grassPositions = this.generateRandomGrassField(n);
        Vector2d tempHigh = new Vector2d(0,0);
        Vector2d tempLow = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for(Vector2d pos: grassPositions){
            tempLow = tempLow.lowerLeft(pos);
            tempHigh = tempHigh.upperRight(pos);
            Grass g = new Grass(pos);
            this.elementList.get(1).add(g);
        }
        this.lower = tempLow;
        this.upper = tempHigh;
        this.visualiser = new MapVisualizer(this);

        System.out.println(tempHigh);
    }


    @Override
    public boolean place(Animal animal) {
        {
            if (canMoveTo(animal.getPosition())) {
                this.elementList.get(0).add(animal);
                this.lower = this.lower.lowerLeft(animal.getPosition());
                this.upper = this.upper.upperRight(animal.getPosition());
                return true;
            }
            return false;
        }
    }

    public Vector2d newGrassPosition(Grass g){ //TODO: change generating method
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
        this.elementList.get(1).add(g);
        this.lower = this.lower.lowerLeft(g.getPosition());
        this.upper = this.upper.upperRight(g.getPosition());
        return true;
    }

    public Grass grassAt(Vector2d pos){
        for (IMapElement el : this.elementList.get(1)) {
            if(el.isAt(pos)){
                return (Grass) el;
            }
        }
        return null;
    }

    @Override
    public void handleMovement(Animal a) {
        Vector2d newPos = a.getPosition();

        Grass g = grassAt(newPos);
        if(g!= null){
            g.position = newGrassPosition(g);
        }

        this.setNewBoundaries();
    }
}
