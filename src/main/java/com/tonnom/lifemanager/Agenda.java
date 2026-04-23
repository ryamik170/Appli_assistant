package com.tonnom.lifemanager;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Agenda {
    @FXML
    private VBox rootBox;
    private int week_number;
    private List<String> jour = List.of("Lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche");
    private   String color = Commun.getFirstLine("Files/Save_color_theme.txt") ;
    public Agenda() {
        System.out.println("Le programme passe d'abord par le constructeur la première fois");
        // il faut que le programme vérifie rapidement si on est dans la bonne semaine
        // je vais faire un script python ici
        if(Commun.getFirstLine("Files/short_files/weeks.txt").equals("none")){// dans ce cas c'est la toute première fois qu'on ouvre le programme
            String date = this.Date().get(1);
            String line = "1-";
            System.out.println("La date d'aujourd'hui est : " + date);
            Commun.write_line("Files/short_files/weeks.txt", line+Commun.split(date, "-").get(2) +"-" +Commun.split(date, "-").get(1));
        }
    }
    public void initialize(){
        String contenu = Commun.getFirstLine("Files/Agenda.txt");
        System.out.println("initilisation lancer");
        if(contenu.equals("first")){
            System.out.println("initilisation lancer pour la deuxième fois");
            first();
        }

    }
    @FXML
    public void first(){ // cette fonction met
        System.out.println("ici");
        GridPane grid = new GridPane();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 7; col++) {

                Button cell = new Button();

                cell.setPrefSize(120, 60);
                if(row == 0) {
                    cell.setText(jour.get(col));
                    cell.setStyle("-fx-background-color: " + color + ";"  + "-fx-border-color: black;" +
                            "-fx-border-width: 3px;");
                }
                grid.add(cell, col, row);
            }
        }

        rootBox.getChildren().add(grid);

    }

    public void loadAgenda(){
        // cette fonction charge l'agenda depuis le fichier texte

    }

    public List<String> Date() {
        LocalDate aujourdhui = LocalDate.now();

        // Récupère le nom complet (Lundi, Mardi...) en français
        String jourSemaine = aujourdhui.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.FRANCE);

        System.out.println("Aujourd'hui, nous sommes : " + jourSemaine);
        return List.of(jourSemaine, aujourdhui.toString());
    }
    public int getWeek(){
        // cette fonction vérifie renvoie le numéro de la semaine à laquelle on est
        String jour = this.Date().get(0); // on recup le jour
        // faut extraire le mois
        return 0;
    }

}
/*
Déroulement : Pour la première fois donc quand le fichier est vide on appelle la methode first et on écrit semaine 1
si c'est pas la première fois on vas appeler la fonction loadAgenda
 */
/*import java.time.LocalDate;

LocalDate aujourdhui = LocalDate.now();
System.out.println(aujourdhui); // Affiche : 2026-04-23*/
// dans le fichier weeks on écrit a chaque fois la date du lundi dans lequel on est ainsi que le numéro de semaine
//num semaine - jours - mois