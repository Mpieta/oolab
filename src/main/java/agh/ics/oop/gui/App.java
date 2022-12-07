package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {
    private AbstractWorldMap map;
    private GridPane gpane;
    private SimulationEngine engine;


    @Override
    public void start(Stage primaryStage) {

        VBox vbox = new VBox();
        HBox hBox = new HBox();
        TextField textField = new TextField();
        Button button = new Button("Start");
        hBox.getChildren().addAll(textField, button);


        GridPane gpane = new GridPane();
        gpane.setMinSize(400,200);
        gpane.setPadding(new Insets(10,10,10,10));
        gpane.setGridLinesVisible(true);

        button.setOnAction(actionEvent -> {
            try{
                this.engine.setMoves(textField.getText());
                Thread engineThread = new Thread((Runnable)engine);
                engineThread.start();
            }
            catch (IllegalArgumentException e){
                System.out.println(e);
            }
        });

        vbox.getChildren().addAll(hBox, gpane);
        this.gpane = gpane;

        Scene scene = new Scene(vbox, 800,800);

        this.setPane(gpane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setPane(GridPane gpane){
        Vector2d lower = this.map.lower;
        Vector2d upper = this.map.upper;

        for (int i=0; lower.x + i <= upper.x+1; i++){
            gpane.getColumnConstraints().add(new ColumnConstraints(55));
        }
        for (int i=0; lower.y + i <= upper.y+1; i++){
            gpane.getRowConstraints().add(new RowConstraints(55));
        }

        Label l = new Label("y\\x");
        gpane.add(l, 0, 0);
        GridPane.setHalignment(l, HPos.CENTER);

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
                        GuiElementBox vbox = new GuiElementBox((IMapElement) obj);
                        Label item = new Label();
                        //GridPane.setHalignment(item, HPos.CENTER);
                        gpane.add(vbox.vbox, x-lower.x+1, upper.y+1-y);
                    }
                }
            }
        }

    }

    public void updateMap(AbstractWorldMap newMap){
        this.map = newMap;

        this.gpane.setGridLinesVisible(false);
        this.gpane.getColumnConstraints().clear();
        this.gpane.getRowConstraints().clear();
        this.gpane.getChildren().clear();
        this.gpane.setGridLinesVisible(true);

        setPane(gpane);
    }


    @Override
    public void init() throws Exception {
        super.init();

        this.map = new GrassField(5);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(4,4)};
        this.engine = new SimulationEngine(map, positions, this);



    }



}
