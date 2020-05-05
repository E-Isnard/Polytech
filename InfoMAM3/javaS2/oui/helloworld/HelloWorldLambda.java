package oui.helloworld;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// version avec lambda
// autre version classique

public class HelloWorldLambda extends Application {

    protected Labeled out;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Say something good");
        Button btn1 = new Button();
        btn1.setText("Say 'Hello'");
        btn1.setOnAction((x) -> out.setText("Hello :-)"));

        out = new Label();

        Button btn2 = new Button("Say yes");
        
        btn2.setText("Say yes");
        btn2.setOnAction((x) -> out.setText("Yes"));

        VBox root = new VBox(30);
        root.getChildren().add(btn1);
        root.getChildren().add(out);
        root.getChildren().add(btn2);
        primaryStage.setScene(new Scene(root, 200, 150));
        primaryStage.show();
    }
}
