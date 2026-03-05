package com.tonnom.lifemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Chrono {

    @FXML
    private Label chronoLabel;

    private int seconds = 0;

    @FXML
    private void handleStart() {
        seconds++;
        chronoLabel.setText("Temps : " + seconds + " sec");
    }
}