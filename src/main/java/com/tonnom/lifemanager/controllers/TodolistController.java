package com.tonnom.lifemanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TodolistController {

    @FXML
    private TextField task; //appel de la balise textfield qui a comme fx:id task

    @FXML
    private VBox taskContainer, helpSentence; //appel de la balise vbox qui a comme fx:id taskContainer

    @FXML
    private Button btnAide;


    @FXML
    private void addNew() {
        String texte = task.getText();  //récupére la tache ecrit sur input
        Text label = new Text("• " + texte); // creation d'une nouvelle balise qui contient la tache


        label.setOnMouseClicked(e -> {label.setStrikethrough(!label.isStrikethrough());}); //equivalent de toggle en Javascript, permet de barrer la tache

        if (texte != "") {
            taskContainer.getChildren().add(label); //ajout de la balise avec la tache dans une autre balise (Vbox)
        }
        task.clear();

    }

    @FXML
    private void suppTask() {
        for (int i = 0; i < taskContainer.getChildren().size(); i++) {
            // Attention faire taskContainer.getChildren()[0] est mauvais car ce n'est pas une liste/tableau/array, c'est une liste observable de noeud
            Text task = (Text) taskContainer.getChildren().get(i); //la solution actuel est de faire un cast pour convertir le type Node qu'est tC.gC.g(i) en Text pour pouvoir utiliser le isStrikethrough()
            if (task.isStrikethrough()){
                taskContainer.getChildren().remove(i);
            }
        }
    }

    @FXML
    private void help() {
        //System.out.println(btnAide.getText());
        if (btnAide.getText().equals("Aide")) {
            Text balise = new Text("Pour supprimer une tâche, il faut d'abord cliquer dessus puis appuyer sur le bouton supprimer.");
            helpSentence.getChildren().add(balise);
            btnAide.setText("Cacher");
            //System.out.println("dedans");

        } else if (btnAide.getText().equals("Cacher")) {
            helpSentence.getChildren().remove(0);
            btnAide.setText("Aide");
        }
    }
}