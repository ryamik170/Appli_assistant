package com.tonnom.lifemanager.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.ArrayList;

public class TodolistController {

    @FXML
    private TextField task; //appel de la balise textfield qui a comme fx:id task

    @FXML
    private VBox taskContainer, helpSentence; //appel de la balise vbox qui a comme fx:id taskContainer

    @FXML
    private Button btnAide;

    //FONCTIONS D'AIDE POUR POUVOIR SAUVEGARDER LES TACHES DANS UN FICHIER TXT, UTILE QUAND LE PROGRAMME SE FERME --> LES TACHES SERONT CONSERVES


    public void addNew(String texte) {
        Text label = new Text("• " + texte);

        label.setOnMouseClicked(e -> {label.setStrikethrough(!label.isStrikethrough());
        });

        taskContainer.getChildren().add(label);
    }

    
    private void load_tasks() {
        try (BufferedReader file= new BufferedReader(new FileReader("Files/Save_task.txt"))) {
            String line;
            while ((line = file.readLine()) != null) {
                addNew(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML 
    public void initialize() {
        load_tasks();
    }

    // FONCTIONS NECESSAIRE A L'AJOUT ET LA SUPPRESSION DES TACHES DE LA TODO LIST

    @FXML
    private void save_task(String task) {
        try (FileWriter file = new FileWriter("Files/Save_task.txt", true)) {
            file.write(task + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addNew() {
        String texte = task.getText();  //récupére la tache ecrit sur input
        Text label = new Text("• " + texte); // creation d'une nouvelle balise qui contient la tache


        label.setOnMouseClicked(e -> {label.setStrikethrough(!label.isStrikethrough());}); //equivalent de toggle en Javascript, permet de barrer la tache

        if (!texte.isEmpty()) {
            taskContainer.getChildren().add(label); //ajout de la balise avec la tache dans une autre balise (Vbox)
            save_task(texte);
        }
        task.clear();
    }

    @FXML
    private void supp_Task_in_file(String The_task) {
        System.out.println("nous sommes dans le fichier");
        Path file_path = Path.of("Files/Save_task.txt");

        try {
            List<String> lines = Files.readAllLines(file_path);
            List<String> newLines = new ArrayList<>();

            for (String line : lines) {
                System.out.println("fct qui supprime dans le file");
                System.out.println(line);
                System.out.println(The_task.substring(0));
                if (!line.equals(The_task.substring(2))) {
                    newLines.add(line);
                }
            }
            Files.write(file_path, newLines);
            //for (String line)

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void suppTask() {
        for (int i = taskContainer.getChildren().size() - 1; i >= 0; i--) {
            // Attention faire taskContainer.getChildren()[0] est mauvais car ce n'est pas une liste/tableau/array, c'est une liste observable de noeud
            Text task = (Text) taskContainer.getChildren().get(i); //la solution actuel est de faire un cast pour convertir le type Node qu'est tC.gC.g(i) en Text pour pouvoir utiliser le isStrikethrough()
            if (task.isStrikethrough()){
                System.out.println(task.getText());
                System.out.println("Prochaine instruction on veut supprimer dans le fichier");
                supp_Task_in_file(task.getText());
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