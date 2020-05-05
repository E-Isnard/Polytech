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

 
// version avec classe interne anonyme
// c'est la version classique que vous trouvez dans les tutoriaux

public class HelloWorld extends Application {
    

	protected Labeled out;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Say something good");
        Button btn1 = new Button();
        btn1.setText("Say 'Hello'");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	out.setText("Hello :-)");
            }
        });
        
        out = new Label();

        VBox root = new VBox(3);
        root.getChildren().add(btn1);
        root.getChildren().add(out);
        primaryStage.setScene(new Scene(root, 200, 150));
        primaryStage.show();
    }
}

