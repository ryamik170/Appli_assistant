package com.tonnom.lifemanager.controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

//Pour le moment on gerera que 2 thèmes pour l'appli : clair et sombre
//Si je veux changer la couleur, je dois acceder à Maincontroller, mais pour ce faire il doit y avoir une liaison entre ces 2 controllers

//par exemple ce qu'il faudrait faire : 

// Charge une nouvelle page dans la zone centrale de l'application.
// On utilise FXMLLoader pour charger le fichier fxml demandé.
//
// Petite amélioration par rapport à l'ancienne version :
// on récupère aussi le controller associé à la page chargée.
//
// Si la page chargée est gérée par SettingController,
// alors on lui donne une référence vers MainController.
// Cela permet à SettingController d'appeler des méthodes du MainController,
// par exemple pour modifier la couleur de fond de l'application.
//
// Ensuite on vide l'ancienne page affichée dans contentArea
// et on ajoute la nouvelle page.

public class SettingController {

    @FXML
    private Button btn;

    @FXML
    private VBox root;

    private MainController mainController;

    //Setter pour utiliser les méthodes de la class MainController
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    //création des nouvelles pages necessaire à setting
    private void New_page(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + page));
            Parent new_page = loader.load();

            // récupérer le controller de la nouvelle page
            Object controller = loader.getController();

            // si le type est SettingController lier avec MainController
            if (controller instanceof SettingController) {
                ((SettingController) controller).setMainController(mainController);
            }

            root.getChildren().setAll(new_page); //on remplace le contenu de la page setting par la page

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void elems_in_setting() {
        String text = btn.getText();

        if (text.equals("Back")) {
            text = "settings";
        }
        New_page(text + "-view.fxml");
    }  

    //sauvegarde de la couleur dans un fichier
    public void save_color(String color) {
        try (FileWriter file = new FileWriter("Files/Save_color_theme.txt")) {
            file.write(color + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete_previous_color() {
        try (FileWriter file = new FileWriter("Files/Save_color_theme.txt")) {
            file.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void change_color(ActionEvent event) {
        Button button = (Button) event.getSource();
        String color = button.getText();
        //System.out.println(color);

        if (mainController != null) {
            //System.out.println("okay");
            mainController.color_of_the_background(color);
            delete_previous_color();
            save_color(color);
        }
        
    }
}