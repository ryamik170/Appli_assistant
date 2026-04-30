package com.tonnom.lifemanager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.VBox;
import javafx.scene.Parent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import com.tonnom.lifemanager.Settings_Theme;

public class MainController {

    @FXML private VBox contentArea;
    
    @FXML private Parent color_text;

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
    public void color_of_the_appli(String color) {
        contentArea.setStyle(color);
        Settings_Theme.setBackground_color(color);
        Settings_Theme.app_color(color_text);
 
    }

    //faire en sorte que la couleur reste meme quand on ferme le programme
    private void load_color() {
        try (BufferedReader file= new BufferedReader(new FileReader("Files/Save_color_theme.txt"))) {
            String line = file.readLine();

            if (line != null) {
                color_of_the_appli(line);
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        load_color();
    }

    @FXML
    private void openTo_do_list() {
        loadPage("To_do_list-view.fxml");
    }
    @FXML
    private void openGame() {loadPage("Games.fxml");}
}