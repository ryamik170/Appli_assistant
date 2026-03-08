package com.tonnom.lifemanager;

import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.time.Instant;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.List;
import java.util.ArrayList;

public class Chrono {

    @FXML
    private Label chronoLabel;
    private  Instant debut;
    private Timeline timeline;
    private int seconds = 0;

    @FXML
    private java.util.List<Long> time(long secondes){
        List<Long> time = new ArrayList<>(List.of(0L, 0L));
        if (secondes < 60){
            time.set(0, 0L);
            time.set(1, secondes);
        }
        else{
            time.set(1, secondes % 60);
            time.set(0, secondes/60);
        }
        return time;
    }
    @FXML
    private void handleStart() {
        debut = Instant.now();
        if (timeline != null) timeline.stop();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            java.time.Duration res = java.time.Duration.between(debut, Instant.now());
            long secondes = res.getSeconds();
            List<Long> actual_time = time(secondes);
            chronoLabel.setText(actual_time.get(0) + " : " + actual_time.get(1));
        }));

        // On dit à la timeline de se répéter indéfiniment
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
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