package com.tonnom.lifemanager.controllers;

// ================= IMPORTS =================
import javafx.fxml.FXML;
import javafx.scene.Parent;
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

import com.tonnom.lifemanager.Commun;
import com.tonnom.lifemanager.Settings_Theme;
import com.tonnom.lifemanager.To_do_list;

// ================= CONTROLLER =================
public class TodolistController {

    // ================= ATTRIBUTS FXML =================
    @FXML private TextField task; // champ de texte pour entrer une tâche
    @FXML private VBox taskContainer, helpSentence; // conteneur des tâches et de l'aide
    @FXML private Button btnAide;
    @FXML private Parent color_text;

    private List<To_do_list> tasks = new ArrayList<>(); //va me permette d'eviter de trop ecrire dans le fichier, et de verifier si une tache est barré ou non

    // =========================================================
    // ================= INITIALISATION =========================
    // =========================================================

    /**
     * Méthode appelée automatiquement à l'ouverture de la page
     * Charge les tâches sauvegardées et applique le thème
     */
    @FXML
    public void initialize() {
        load_tasks();
        Settings_Theme.app_color(color_text);
        task.setOnAction(e -> addNew()); //enter
    }

    // =========================================================
    // ================= GESTION AFFICHAGE ======================
    // =========================================================

    /**
     * Ajoute une nouvelle tâche visuellement dans la liste, methode necessaire dans le cas ou veut garder les taches dans la todolist si jamais on quitte l'appli, methode appelé par load_tasks
     */
    public void addNew(String texte, boolean task_is_over) {
        To_do_list todolist = new To_do_list(texte, task_is_over);
        tasks.add(todolist);


        Text label = new Text("• " + texte);
        label.setStrikethrough(task_is_over);
        System.out.println(todolist.isDone());

        label.setOnMouseClicked(e -> {
            System.out.println(!todolist.isDone());
            todolist.setDone(!todolist.isDone());
            label.setStrikethrough(todolist.isDone());
            save_task();
        });

        taskContainer.getChildren().add(label);
    }

    /**
     * Ajoute une nouvelle tâche depuis le champ texte
     */
    @FXML
    private void addNew() {
        String texte = task.getText();

        if (!texte.isEmpty()) {
            addNew(texte, false);
            save_task();
        }

        task.clear();
    }

    /**
     * Supprime les tâches barrées (sur l'appli + dans le file)
     */
    @FXML
    private void suppTask() {
        for (int i = tasks.size() - 1; i >= 0; i--) {

            if (tasks.get(i).isDone()) {
                System.out.println(tasks.get(i).getTask());
                tasks.remove(i);
                taskContainer.getChildren().remove(i);
            }
        }
        save_task();
    }

    // =========================================================
    // ================= GESTION FICHIER ========================
    // =========================================================

    /**
     * Sauvegarde une tâche dans le fichier texte
     */
    @FXML
    private void save_task() {
        try (FileWriter file = new FileWriter("Files/Save_task.txt")) {

            for (To_do_list td : tasks) {
                file.write(td.getTask() + "|" + td.isDone() + "\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge toutes les tâches depuis le fichier texte
     */
    private void load_tasks() {
        try (BufferedReader file = new BufferedReader(new FileReader("Files/Save_task.txt"))) {
            String line;

            while ((line = file.readLine()) != null) {
                List<String> list = Commun.split(line, "|");

                String task = list.get(0);
                boolean task_is_over = Boolean.parseBoolean(list.get(1));

                addNew(task, task_is_over);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime une tâche spécifique du fichier texte
     */
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =========================================================
    // ================= AIDE UTILISATEUR =======================
    // =========================================================

    /**
     * Affiche ou cache le message d'aide
     */
    @FXML
    private void help() {

        if (btnAide.getText().equals("Aide")) {

            Text balise = new Text(
                "Pour supprimer une tâche, il faut d'abord cliquer dessus puis appuyer sur le bouton supprimer."
            );

            helpSentence.getChildren().add(balise);
            btnAide.setText("Cacher");

        } else if (btnAide.getText().equals("Cacher")) {

            helpSentence.getChildren().remove(0);
            btnAide.setText("Aide");
        }
    }
}