package com.tonnom.lifemanager;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.time.Instant;

public class TimeManager {
    private static TimeManager instance;
    private Instant debut;
    private Timeline calculTimeline;
    private long secondesEcoulees = 0;
    private long tempsAccumule = 0; // Stocke le temps des sessions précédentes

    private TimeManager() {}

    public static TimeManager getInstance() {
        if (instance == null) instance = new TimeManager();
        return instance;
    }

    public void demarrer() {
        if (estEnMarche()) return; // Évite de lancer plusieurs fois

        debut = Instant.now();
        calculTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Temps total = temps déjà fait + temps de la session actuelle
            secondesEcoulees = tempsAccumule + java.time.Duration.between(debut, Instant.now()).getSeconds();
        }));
        calculTimeline.setCycleCount(Animation.INDEFINITE);
        calculTimeline.play();
    }

    public void mettrePause() {
        if (calculTimeline != null) {
            calculTimeline.stop();
            tempsAccumule = secondesEcoulees; // On sauvegarde le temps actuel
            calculTimeline = null;
        }
    }

    public void reinitialiser() {
        if (calculTimeline != null) calculTimeline.stop();
        secondesEcoulees = 0;
        tempsAccumule = 0;
        debut = null;
        calculTimeline = null;
    }

    public long getSecondesEcoulees() { return secondesEcoulees; }
    public boolean estEnMarche() { return calculTimeline != null && calculTimeline.getStatus() == Animation.Status.RUNNING; }
}