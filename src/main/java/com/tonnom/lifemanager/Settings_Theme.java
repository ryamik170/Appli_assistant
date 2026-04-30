package com.tonnom.lifemanager;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.Parent;

//utilisation et renommage du fichier Settings.java (autant l'utiliser tant qu'a faire)
//regrouper toute les fonctions eparpiller un peu partout dans le projet utile par le theme de la partie setting


public class Settings_Theme {

    private static String background_color = "cyan";

    private static Map<String, String> dico_matching_colors = new HashMap<>(Map.of(
            "red", "white",
            "cyan", "black",
            "pink", "#3A1F2B",
            "blue", "white",
            "yellow", "black",
            "white", "black",
            "orange", "black"
        ));

    private static Map<String, String> dico_button_background_colors = new HashMap<>(Map.of(
            "white", "#1F2937",   
            "black", "#E5E7EB",  
            "#3A1F2B", "#FBCFE8" 
    ));
    
    public static void setBackground_color(String new_Background_color) {
        background_color = new_Background_color;
    }

    public static void app_color(Parent color_text) {
        String color_theme = dico_matching_colors.get(background_color);
        String color_background_button = dico_button_background_colors.get(color_theme);

        color_text.setStyle("-fx-background-color: " + background_color + ";");

        for (Node node : color_text.lookupAll(".label")) {
            node.setStyle(
                "-fx-text-fill: " + color_theme + ";" +
                "-fx-font-family: 'Arial'; -fx-font-weight: bold;"
            );
        }

        for (Node node : color_text.lookupAll(".button")) {
            node.setStyle(
                "-fx-text-fill: " + color_theme + ";" +
                "-fx-background-color: " + color_background_button + ";" +
                "-fx-border-color: black;"
            );
        }
    }
}
