package oui.layouts;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.text.Font;

public class Calc extends Application {

    public void start(Stage primaryStage) {

        primaryStage.setTitle("Calc");
        Text x = new Text("x");
        Text y = new Text("y");
        Text out = new Text();

        TextField xField = new TextField();
        xField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        TextField yField = new TextField();
        yField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));

        Text res = new Text("Résultat: ");

        x.setFont(Font.font("Verdana", 20));
        y.setFont(Font.font("Verdana", 20));
        res.setFont(Font.font("Verdana", 20));
        out.setFont(Font.font("Verdana", 20));
        Button plus = new Button("+");
        Button moins = new Button("-");
        Button fois = new Button("x");
        Button division = new Button("÷");

        moins.setTranslateY(-35);
        moins.setTranslateX(25);
        fois.setTranslateY(-45);
        division.setTranslateY(-80);
        division.setTranslateX(25);
        res.setTranslateY(-80);
        out.setTranslateY(-115);
        out.setTranslateX(90);
        plus.setOnAction((z) -> out.setText(String.valueOf(
                Integer.parseInt(xField.getText()) + Integer.parseInt(yField.getText()))));
        
        moins.setOnAction((z) -> out
                .setText(String.valueOf(Integer.parseInt(xField.getText()) - Integer.parseInt(yField.getText()))));
        
        fois.setOnAction((z) -> out
                .setText(String.valueOf(Integer.parseInt(xField.getText()) * Integer.parseInt(yField.getText()))));
        
        division.setOnAction((z) -> out
                .setText(String.valueOf(Double.parseDouble(xField.getText()) / Double.parseDouble(yField.getText()))));
                

        VBox root = new VBox(10);

        root.getChildren().addAll(x, xField, y, yField, plus, moins, fois, division, res, out);

        // root2.setTranslateY(-30);
        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();

    }

}