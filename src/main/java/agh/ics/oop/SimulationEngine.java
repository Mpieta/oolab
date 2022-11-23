package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] initialPositions;

    private ArrayList<Animal> animalList;

    private JTextPane textPane;
    private Timer timer;
    private ArrayList<String> steps;

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

    private JTextPane createFrame(AbstractWorldMap map){
        Vector2d upper = map.upper;
        Vector2d lower = map.lower;

        Vector2d size = new Vector2d(400,400);

        JFrame frame = new JFrame();
        frame.setTitle("Animals");
        frame.setSize(size.x, size.y);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBounds(0,0,size.x,size.y);

        Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);
        textPane.setFont(font);

        frame.add(textPane);
        frame.setVisible(true);

        textPane.setText(this.steps.get(0));
        this.timer = new Timer(500, e -> {
            if(steps.size()==0){
                timer.stop();
                return;
            }
            textPane.setText(steps.get(0));
            steps.remove(0);
        });
        timer.setRepeats(true);
        timer.start();
        return textPane;
    }


    @Override
    public void run(){
        this.steps = new ArrayList<>();
        this.steps.add(map.toString());

        for(int i = 0; i<moves.length;i++) {
            Animal temp = this.animalList.get(i%this.animalList.size());
            temp.move(this.moves[i]);
            map.handleMovement(temp);
            this.steps.add(map.toString());
        }
        this.textPane = createFrame((AbstractWorldMap) map);
    }
}
