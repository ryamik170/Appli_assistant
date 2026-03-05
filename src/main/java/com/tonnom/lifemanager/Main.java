package com.tonnom.lifemanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/chrono-view.fxml"));

        // Largeur (ex: 800) et hauteur (ex: 600)
        Scene scene = new Scene(loader.load(), 800, 600);

        stage.setTitle("Life Manager - Chrono");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}// src/main/java/org/example/Main.java