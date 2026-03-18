package com.tonnom.lifemanager.controllers;

import com.tonnom.lifemanager.Games;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {

    @FXML
    private VBox contentArea;

    private void loadPage(String page) {
        try {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(
                    FXMLLoader.load(getClass().getResource("/view/" + page))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openChrono() {
        loadPage("chrono-view.fxml");
    }

    @FXML
    private void openAgenda() {
        loadPage("agenda-view.fxml");
    }

    @FXML
    private void openStats() {
        loadPage("stats-view.fxml");
    }

    @FXML
    private void openSettings() {
        loadPage("settings-view.fxml");
    }

    @FXML
    private void openTo_do_list() {
        loadPage("To_do_list-view.fxml");
    }
    @FXML
    private void openGame() {loadPage("Games.fxml");}
}