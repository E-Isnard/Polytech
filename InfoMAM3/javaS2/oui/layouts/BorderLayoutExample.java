package oui.layouts;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Illustrate the layout style of a BorderLayout.
 * 
 * @author helen from David J. Barnes and Michael Kolling

 */
public class BorderLayoutExample extends Application {
    
    
    @Override
    /**
     * Place five components within a container managed by
     * a FlowLayout.
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BorderLayout Example");
              
        BorderPane root = new BorderPane();
    	root.setTop(new Button("north"));
        root.setBottom(new Button("south"));
        root.setCenter(new Button("center"));
        root.setLeft(new Button("west"));
        root.setRight(new Button("east"));
        primaryStage.setScene(new Scene(root, 500, 250));
        primaryStage.show();

    }
    

}
