package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] initialPositions;
    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions) {
        this.moves = moves;
        this.map = map;
        this.initialPositions = positions;

        for(int i = 0;i<initialPositions.length;i++) {
            Animal a = new Animal(map,positions[i]);
            this.map.place(a);
        }
    }


    @Override
    public void run() throws InterruptedException {
        if(this.map instanceof RectangularMap) {
            ArrayList<Animal> animalList = ((RectangularMap) this.map).animalList;
            //System.out.println((RectangularMap)this.map);

            JFrame f = new JFrame("animals");
            JTextArea t = new JTextArea("");
            JPanel p = new JPanel();
            p.add(t);
            f.add(p);

            f.setSize(300,300);
            f.show();
            for(int i = 0; i<moves.length;i++) {
                Thread.sleep(500);
                animalList.get(i%animalList.size()).move(this.moves[i]);
                String str = this.map.toString();
                String currMove = this.moves[i].toString();
                f.setTitle(currMove);
                t.setText(str);
            }
            System.out.println((RectangularMap)this.map);
        }

    }
}