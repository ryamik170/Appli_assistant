package com.tonnom.lifemanager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


//Pour le moment on gerera que 2 thèmes pour l'appli : clair et sombre

public class SettingController {

    @FXML
    private Button btn;

    @FXML
    private VBox root;

    private void New_page(String page) {
        try {
            Parent new_page = FXMLLoader.load(getClass().getResource("/view/" + page));

            root.getChildren().setAll(new_page); //on remplace le contenu de setting

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void elems_in_setting() {
        String text = btn.getText();
        System.out.println(text);


        New_page(text + "-view.fxml");

    }   
}