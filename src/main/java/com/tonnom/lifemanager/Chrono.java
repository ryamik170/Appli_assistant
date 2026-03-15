package com.tonnom.lifemanager;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import  java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Chrono {

    @FXML private Label chronoLabel;
    @FXML private Button pause;
    @FXML private Button fin;
    @FXML private Button GO;
    private Timeline rafraichisseurVisuel; // Boucle locale d'affichage
    private LocalDateTime deb;

    // Cette méthode s'exécute automatiquement à chaque fois que l'onglet s'affiche
    @FXML
    public void initialize() {
        // 1. On affiche direct la valeur actuelle (ex: si on revient d'un autre onglet)
        mettreAJourLeTexte();

        // 2. Si le moteur tourne, on lance la boucle qui rafraîchit le Label
        if (TimeManager.getInstance().estEnMarche()) {
            lancerBoucleAffichage();
        }
    }

    @FXML
    private void handleStart() {
        GO.setVisible(false);GO.setManaged(false);
        fin.setVisible(true); fin.setManaged(true); pause.setVisible(true);pause.setManaged(true);
        TimeManager.getInstance().demarrer();
        deb = LocalDateTime.now();
        System.out.println("Debut : " + deb);
        /*try{
            this.encoding();
        } catch (IOException e){
            e.printStackTrace();
        }*/
        lancerBoucleAffichage();
    }

    private void lancerBoucleAffichage() {
        if (rafraichisseurVisuel != null) rafraichisseurVisuel.stop();

        // On crée une petite boucle qui demande l'heure au moteur 2 fois par seconde
        rafraichisseurVisuel = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            mettreAJourLeTexte();
        }));
        rafraichisseurVisuel.setCycleCount(Animation.INDEFINITE);
        rafraichisseurVisuel.play();
    }

    private void mettreAJourLeTexte() {
        long totalSecs = TimeManager.getInstance().getSecondesEcoulees();
        long mins = totalSecs / 60;
        long secs = totalSecs % 60;
        // On met à jour le Label sur l'écran
        chronoLabel.setText(String.format("%d : %02d", mins, secs));
    }

    public void encoding() throws IOException{
        String tram; String deb_t = deb.toString();
        BufferedWriter file = new BufferedWriter(new FileWriter("Files/Working_Data.txt", true));
        LocalDateTime now = LocalDateTime.now();tram = now.toString();
        System.out.println("Fin : " + now);
        String res = tram.substring(0, 4) + "." + tram.substring(5, 7) + "." + tram.substring(8, 10) + "/" + deb_t.substring(11, 16) + "/" + tram.substring(11, 16);
        file.write(res);
        file.newLine();;
        file.close();

    }
}

/*
Pour avoir la date et l'heure précise

import java.time.LocalDateTime;

LocalDateTime now = LocalDateTime.now();
outpout : 2026-03-10T15:42:18.123
System.out.println(now);
 */
/*
Catégorie,Méthode,Action
Création,Instant.now(),Obtient l'instant actuel.
,Instant.ofEpochMilli(long),Instancie à partir des millisecondes écoulées depuis le 1er janvier 1970.
,Instant.ofEpochSecond(long),Instancie à partir des secondes écoulées depuis le 1er janvier 1970.
,Instant.parse(CharSequence),"Instancie à partir d'une chaîne au format ISO-8601 (ex: ""2026-03-06T10:00:00Z"")."
Comparaison,isAfter(Instant),Vérifie la postériorité par rapport à un autre instant.
,isBefore(Instant),Vérifie l'antériorité par rapport à un autre instant.
,equals(Object),Vérifie l'égalité avec un autre instant.
Manipulation,plusMillis(long),Ajoute des millisecondes.
,plusSeconds(long),Ajoute des secondes.
,minusMillis(long),Soustrait des millisecondes.
,minusSeconds(long),Soustrait des secondes.
Extraction,toEpochMilli(),Retourne le nombre total de millisecondes depuis l'époque.
,getEpochSecond(),Retourne le nombre total de secondes depuis l'époque.
*/