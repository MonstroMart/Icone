package net.franksuselessworld.icone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Icone extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent icone = FXMLLoader.load(getClass().getResource("Icone.fxml"));
        primaryStage.setTitle("Icone");
        primaryStage.setScene(new Scene(icone, 1111, 718));
        primaryStage.show();
    }
}