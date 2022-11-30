package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;

public class App extends Application {
    private AbstractWorldMap map;


    @Override
    public void start(Stage primaryStage) {


        GridPane gpane = new GridPane();
        gpane.setMinSize(400,200);
        gpane.setPadding(new Insets(10,10,10,10));
        gpane.setGridLinesVisible(true);

        Label label = new Label("Zwierzak");
        Scene scene = new Scene(gpane, 400,400);

        this.setPane(gpane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setPane(GridPane gpane){
        Vector2d lower = this.map.lower;
        Vector2d upper = this.map.upper;

        for (int i=0; lower.x + i <= upper.x+1; i++){
            gpane.getColumnConstraints().add(new ColumnConstraints(20));
        }
        for (int i=0; lower.y + i <= upper.y+1; i++){
            gpane.getRowConstraints().add(new RowConstraints(20));
        }

        Label l = new Label("y/x");
        gpane.add(l, 0, 0);

        for (int i = lower.x; i <= upper.x; i++){
            Label new_element = new Label(Integer.toString(i));
            GridPane.setHalignment(new_element, HPos.CENTER);
            gpane.add(new_element, i - lower.x+1, 0);
        }

        for (int i = lower.y; i <= upper.y; i++){
            Label new_element = new Label(Integer.toString(i));
            GridPane.setHalignment(new_element, HPos.CENTER);
            gpane.add(new_element, 0, upper.y+1-i);
        }



        for (int x = lower.x; x<=upper.x; x++){
            for (int y = lower.y; y<=upper.y; y++){
                if (map.isOccupied(new Vector2d(x,y))){
                    Object obj = this.map.objectAt(new Vector2d(x,y));
                    if (obj != null){
                        Label item = new Label(obj.toString());
                        GridPane.setHalignment(item, HPos.CENTER);
                        gpane.add(item, x-lower.x+1, upper.y+1-y);
                    }
                }
            }
        }

    }


    @Override
    public void init() throws Exception {
        super.init();
        MoveDirection[] directions = null;
        try{
            List<String> argsList = getParameters().getRaw();
            String[] args = new String[argsList.size()];
            argsList.toArray(args);
            directions = new OptionsParser().parse(args);
        }catch(IllegalArgumentException exc){
            System.out.println(exc);
            directions = new MoveDirection[]{};

        }

        this.map = new GrassField(5);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(0,0)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();



    }



}
