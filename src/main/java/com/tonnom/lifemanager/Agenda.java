package com.tonnom.lifemanager;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
public class Agenda {
    @FXML
    private VBox rootBox;
    private List<String> month = List.of("Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre");
    private LocalDate today = LocalDate.now();
    private List<String> jour = List.of("Lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche");
    List<String> week_day = new ArrayList<>();// la date des jours de la semaine
    private String color = Commun.getFirstLine("Files/Save_color_theme.txt") ;
    public Agenda() {
        NumberWeekDays();
        String date = this.Date().get(1);
        int week = getWeek(date);
        System.out.println("Le jour est : " + Commun.split(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toString(), "-").get(2));
        //LocalDate premierJour = LocalDate.now().with(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek());
        //lun = Integer.parseInt(Commun.split(premierJour.toString(), "-").get(2));
        if((Commun.getFirstLine("Files/short_files/weeks.txt").equals("none")) || ( week != (int) Commun.getFirstLine("Files/short_files/weeks.txt").charAt(0))){// dans ce cas c'est la toute première fois qu'on ouvre le programme
            String line = String.valueOf(week) + "-" + Commun.split(date, "-").get(2) +"-" +Commun.split(date, "-").get(1);
            Commun.write_line("Files/short_files/weeks.txt", line);
        }
        //Faut effacer les trucs dans le agenda qui ne sont plus valable
        else{ // ici on sait que on a un agenda a charger

        }

    }
    public void initialize(){
        String contenu = Commun.getFirstLine("Files/Agenda.txt");
        if(contenu.equals("first")){
            calendar();
        }

    }
    @FXML
    public void calendar(){ // cette fonction met

        HBox topBar = new HBox();
        topBar.setPrefHeight(60);
        topBar.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");
        Label title = new Label(month.get(Integer.parseInt(Commun.split(today.toString(), "-").get(1)) - 1));
        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().add(title);

        rootBox.getChildren().add(topBar);

        System.out.println("ici");
        GridPane grid = new GridPane();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 7; col++) {

                Button cell = new Button();

                cell.setPrefSize(120, 60);
                if(row == 0) { // faut rajouter la date ici
                    cell.setText(jour.get(col) + " " +  week_day.get(col));
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
        //LocalDate today = LocalDate.now();

        // Récupère le nom complet (Lundi, Mardi...) en français
        String jourSemaine = today.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.FRANCE);

        System.out.println("Aujourd'hui, nous sommes : " + jourSemaine);
        return List.of(jourSemaine, today.toString());
    }
    void NumberWeekDays(){
        week_day.add(Commun.split(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toString(), "-").get(2));
        week_day.add(Commun.split(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.TUESDAY)).toString(), "-").get(2));
        week_day.add(Commun.split(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.WEDNESDAY)).toString(), "-").get(2));
        week_day.add(Commun.split(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY)).toString(), "-").get(2));
        week_day.add(Commun.split(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY)).toString(), "-").get(2));
        week_day.add(Commun.split(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY)).toString(), "-").get(2));
        week_day.add(Commun.split(today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).toString(), "-").get(2));


    }
    public int getWeek(String date){
        // cette fonction vérifie renvoie le numéro de la semaine à laquelle on est
        int weekNumber = LocalDate.parse(date).get(WeekFields.of(Locale.getDefault()).weekOfYear());
        // faut extraire le mois
        return weekNumber;
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
/*
   import java.util.Calendar;
   import java.util.Date;

   public class WeekNumber {
       public static void main(String[] args) {
           // Créez une date
           Date d = new Date("2023-01-15");

           Calendar calendar = Calendar.getInstance();
           int year = calendar.get(Calendar.YEAR);
           int week = calendar.get(Calendar.ISO_WEEKDAY); // Note: Retourne 1 pour le lundi

           System.out.println("Année:", year);
           System.out.println("Numéro de semaine:", week);
       }
   }
   ```*/