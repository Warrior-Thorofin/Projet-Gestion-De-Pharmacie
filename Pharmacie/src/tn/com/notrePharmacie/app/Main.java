package app;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
    	

    	Parent root= FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/tst.fxml"));
    	Scene scene = new Scene(root);
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


        primaryStage.setScene(scene);
        primaryStage.setTitle("pharmacie l insat ");
        primaryStage.show(); // indispensable !
    }

    public static void main(String[] args) {
        launch(args);
    }
}