package oui.layouts;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Illustrate the layout style of a HBox.
 * 
 * @author helen from David J. Barnes and Michael Kolling

 */
public class BoxLayoutExample extends Application{
	   
	    
	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("HBox Example");
	        HBox box = new HBox();
	        box.getChildren().add(new Button("first"));
	        box.getChildren().add(new Button("second"));
	        box.getChildren().add(new Button("the third string is very long"));
	        box.getChildren().add(new Button("fourth"));
	        box.getChildren().add(new Button("fifth"));
	        box.setSpacing(3);
	        primaryStage.setScene(new Scene(box,300,300));
        primaryStage.show();
    }
}
