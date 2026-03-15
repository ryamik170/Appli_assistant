package com.tonnom.lifemanager;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.time.Instant;

public class TimeManager {
    private static TimeManager instance; // L'unique instance (Singleton)
    private Instant debut;
    private Timeline calculTimeline;
    private long secondesEcoulees = 0;

    // Constructeur privé pour empêcher le "new TimeManager()" ailleurs
    private TimeManager() {}

    public static TimeManager getInstance() {
        if (instance == null) {
            instance = new TimeManager();
        }
        return instance;
    }

    public void demarrer() {
        // Si le chrono tourne déjà, on ne fait rien ou on recommence
        if (calculTimeline != null) calculTimeline.stop();
        System.out.println("set elem");
        debut = Instant.now();
        calculTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // On calcule l'écart entre le moment du clic et maintenant
            secondesEcoulees = java.time.Duration.between(debut, Instant.now()).getSeconds();
        }));
        calculTimeline.setCycleCount(Animation.INDEFINITE);
        calculTimeline.play();
    }

    public long getSecondesEcoulees() {
        return secondesEcoulees;
    }

    public boolean estEnMarche() {
        return calculTimeline != null && calculTimeline.getStatus() == Animation.Status.RUNNING;
    }

    public Instant getDebut() {
        System.out.println("GET ELEM");
        return debut;
    }
}
