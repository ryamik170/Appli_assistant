package com.tonnom.lifemanager;
import javafx.fxml.FXML;
public class Games {

    @FXML
    private void Start_game() {
        try {
            Runtime.getRuntime().exec("Files/mikob/run.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

