package oui.layouts;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Illustrate the layout style of a FlowPane.
 * 
 * from David J. Barnes and Michael Kolling
 */
public class FlowLayoutExample extends Application {
    
    @Override
    /**
     * Place five components within a container managed by
     * a FlowLayout.
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Flow layout example");
              
        FlowPane root = new FlowPane();
        root.getChildren().add(new Button("first"));
        root.getChildren().add(new Button("second"));
        root.getChildren().add(new Button("the third string is very long"));
        root.getChildren().add(new Button("fourth"));
        root.getChildren().add(new Button("fifth"));
        root.setPadding(new Insets(10, 10, 10, 10));
        primaryStage.setScene(new Scene(root, 100, 250));
        primaryStage.show();


    }
}
