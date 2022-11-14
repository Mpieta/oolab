package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;


public class GrassField implements IWorldMap{

    Vector2d max;
    Vector2d min;

    public ArrayList<Animal> animalList;
    public ArrayList<Grass> grassList;
    MapVisualizer visualiser;


    public GrassField(int n) {
        int min_x = Integer.MAX_VALUE;
        int min_y = Integer.MAX_VALUE;

        int max_x = 0;
        int max_y = 0;
        ArrayList<Grass> grasses = new ArrayList<Grass>();
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

                int rnd = Math.abs(rng.nextInt());
                if(rnd%fields_left < grass_left) {
                    if(i < min_x) {
                        min_x = i;
                    }

                    if(i > max_x) {
                        max_x =i;
                    }

                    if(j<min_y){
                        min_y = j;
                    }

                    if(j>max_y){
                        max_y=j;
                    }


                    current_num +=1;
                    Vector2d p = new Vector2d(i,j);
                    grassPositions.add(p);
                    System.out.println(p);

                }
            }
        }
        for(Vector2d pos: grassPositions){
            Grass g = new Grass(pos);
            grasses.add(g);
        }
        this.animalList = new ArrayList<Animal>();
        this.grassList = grasses;
        this.min = new Vector2d(min_x, min_y);
        this.max = new Vector2d(max_x, max_y);
        this.visualiser = new MapVisualizer(this);

        System.out.println(this.grassList);
    }

    public String toString() {
        return this.visualiser.draw(min,max);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        for(Animal a: animalList){
            if(a.isAt(position)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())) {
            System.out.println("TR");
            this.animalList.add(animal);
            this.min = this.min.lowerLeft(animal.getPosition());
            this.max = this.max.upperRight(animal.getPosition());
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal a: animalList) {
            if(a.isAt(position)) {
                return true;
            }
        }

        for(Grass g: grassList) {
            if(g.isAt(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal a: animalList) {
            if(a.isAt(position)) {
                return a;
            }
        }

        for(Grass g: grassList) {
            if(g.isAt(position)) {
                return g;
            }
        }
        return null;
    }
}
