package com.tonnom.lifemanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/main-view.fxml")
        );

        Scene scene = new Scene(loader.load(), 1000, 700);

        stage.setTitle("Life Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}// src/main/java/org/example/Main.java