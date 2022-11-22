package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] initialPositions;

    private ArrayList<Animal> animalList;
    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions) {
        this.moves = moves;
        this.map = map;
        this.initialPositions = positions;
        this.animalList = new ArrayList<Animal>();

        for(int i = 0;i<initialPositions.length;i++) {
            Animal a = new Animal(map,positions[i]);
            this.animalList.add(a);
            this.map.place(a);
        }
    }


    @Override
    public void run() throws InterruptedException {
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
            Animal temp = this.animalList.get(i%this.animalList.size());
            temp.move(this.moves[i]);
            map.handleMovement(temp);
            String str = this.map.toString();
            String currMove = this.moves[i].toString();
            f.setTitle(currMove);
            t.setText(str);
        }
            System.out.println(this.map);
    }
}
