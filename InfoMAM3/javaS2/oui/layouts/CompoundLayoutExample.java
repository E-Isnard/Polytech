package oui.layouts;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Illustrate the layout style of a BorderLayout.
 * 
 * @author helen from David J. Barnes and Michael Kolling
 */
public class CompoundLayoutExample extends Application {
    
    
    @Override
    /**
     * Place five components within a container managed by
     * a FlowLayout.
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BorderLayout Example");
              
        BorderPane root = buildRoot(6);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

    }
    
    private BorderPane buildRoot(int n){
    	// à compléter
        return null;
    }
 
}
