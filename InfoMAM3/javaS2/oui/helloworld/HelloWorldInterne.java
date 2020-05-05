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

//  gestion des événements avec classes internes
 
public class HelloWorldInterne extends Application {
	Button btn;
	protected Labeled out;
	

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Say something good");
        btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new ActionHello());
 
        out = new Label();

        VBox root = new VBox(3);
        root.getChildren().add(btn);
        root.getChildren().add(out);
        primaryStage.setScene(new Scene(root, 200, 150));
        primaryStage.show();
    }

    private class ActionHello implements  EventHandler<ActionEvent> {
    	@Override
    	public void handle(ActionEvent event) {
    		out.setText("Hello");
    	}
    }

}

