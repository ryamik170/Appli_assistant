package com.tonnom.lifemanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TodolistController {

    @FXML
    private TextField task;

    @FXML
    private VBox taskContainer;

    @FXML
    private void addNew() {
        String texte = task.getText();  //récupére la tache ecrit sur input
        Text label = new Text(texte); // creation d'une nouvelle balise qui contient la tache


        label.setOnMouseClicked(e -> {label.setStrikethrough(!label.isStrikethrough());}); //equivalent de toggle en Javascript, permet de barrer la tache

        taskContainer.getChildren().add(label); //ajout de la balise avec la tache dans une autre balise (Vbox)
        task.clear();

    }

    @FXML
    private void suppTask() {}

    @FXML
    private void help() {}
}