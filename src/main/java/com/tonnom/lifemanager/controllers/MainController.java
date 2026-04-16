package com.tonnom.lifemanager.controllers;

import com.tonnom.lifemanager.Games;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;

import java.io.IOException;

public class MainController {

    @FXML
    private VBox contentArea;

    //leger modification de loadpage(en gardant sa fonctionnalité) permettant une lisaison entre Maincontroller et SettingController
    //information dans SettingController.java
    private void loadPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + page));
            Parent root = loader.load();
            Object controller = loader.getController();

            if (controller instanceof SettingController) {
                ((SettingController) controller).setMainController(this);
            }
            
            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);

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
    public void color_of_the_background(String color) {
        contentArea.setStyle("-fx-background-color: " + color + ";");
    }

    @FXML
    private void openTo_do_list() {
        loadPage("To_do_list-view.fxml");
    }
    @FXML
    private void openGame() {loadPage("Games.fxml");}
}