package oui.layouts;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Illustrate the layout style of a FlowPane.
 * 
 * from David J. Barnes and Michael Kolling
 */
public class GridLayoutExample extends Application {
 
    @Override
    /**
     * Place five components within a container managed by
     * a FlowLayout.
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Grid layout example");
              
        GridPane root = new GridPane();
        root.add(new Button("first"),1,0);
        root.add(new Button("second"),0,1);
        root.add(new Button("the third string is very long"),1,1);
        root.add(new Button("fourth"),1,2);
        root.add(new Button("fifth"),2,1);
        root.setGridLinesVisible(true);
        primaryStage.setScene(new Scene(root, 500, 250));
        primaryStage.show();


    }
}
