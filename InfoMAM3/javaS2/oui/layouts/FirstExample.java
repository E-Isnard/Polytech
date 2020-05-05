package oui.layouts;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

 
public class FirstExample extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Un magnifique titre");
        Label lab1 = new Label();
        lab1.setText("Hello World");
       
        FlowPane root = new FlowPane();
        root.getChildren().add(lab1);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }
}

