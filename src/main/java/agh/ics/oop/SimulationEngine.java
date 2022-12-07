package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;


import java.util.ArrayList;


public class SimulationEngine implements IEngine, Runnable{
    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] initialPositions;

    private ArrayList<Animal> animalList;
    private App app;
    int moveDelay = 300;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions) {
        this.moves = moves;
        this.map = map;
        this.initialPositions = positions;
        this.animalList = new ArrayList<Animal>();

        for(int i = 0;i<initialPositions.length;i++) {
            try{
                Animal a = new Animal(map,positions[i]);
                this.animalList.add(a);
                this.map.place(a);
            }
            catch(IllegalArgumentException exc){
                System.out.println(exc);
            }

        }
    }

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions, App app) {
        this.moves = moves;
        this.map = map;
        this.initialPositions = positions;
        this.animalList = new ArrayList<Animal>();
        this.app = app;

        for(int i = 0;i<initialPositions.length;i++) {
            try{
                Animal a = new Animal(map,positions[i]);
                this.animalList.add(a);
                this.map.place(a);
            }
            catch(IllegalArgumentException exc){
                System.out.println(exc);
            }

        }
    }

    public SimulationEngine(IWorldMap map, Vector2d[] positions, App app) {
        this.map = map;
        this.initialPositions = positions;
        this.animalList = new ArrayList<Animal>();
        this.app = app;

        for(int i = 0;i<initialPositions.length;i++) {
            try{
                Animal a = new Animal(map,positions[i]);
                this.animalList.add(a);
                this.map.place(a);
            }
            catch(IllegalArgumentException exc){
                System.out.println(exc);
            }

        }
    }

    public void setMoves(String s){
        String[] x = s.split(" ");
        this.moves = new OptionsParser().parse(x);
    }

    @Override
    public synchronized void run(){
        for(int i = 0; i<moves.length;i++) {
            Animal temp = this.animalList.get(i%this.animalList.size());
            temp.move(this.moves[i]);
            map.handleMovement(temp);
            Platform.runLater(() ->{
                this.app.updateMap((AbstractWorldMap) this.map);
            });
            try{
                Thread.sleep(moveDelay);
            }
            catch(InterruptedException exc){
                System.out.println(exc);
            }
        }
    }
}
