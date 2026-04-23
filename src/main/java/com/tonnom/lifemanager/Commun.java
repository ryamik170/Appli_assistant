package com.tonnom.lifemanager;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Commun {

    public static List<String> split(String chaine, String car){
        // cette fonction prend une chaine de caractere et la split en fonction du car donner en parametre
        List<String> list = new ArrayList<>();
        String var = "";
        for(char ch : chaine.toCharArray()){
            if(String.valueOf(ch).equals(car)){
                list.add(var);
                var = "";
            }
            else {

                var+= ch;
            }

        }
        if(!var.isEmpty()) list.add(var);
        return list;
    }
    public static void write_line(String path, String line){ //cette methode ecris une ligne dans un fichier qui n'en contient qu'une
        try (BufferedWriter file = new BufferedWriter(new FileWriter(path))) {
            file.write(line);
            file.newLine(); // saut de ligne
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void add_line(String path, String line){//Cette methode ajoute une line a un fichier qui en contient deja ou pas
        try (BufferedWriter file = new BufferedWriter(new FileWriter(path, true))) {
            file.write(line);
            file.newLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getFirstLine(String path){
        try (BufferedReader file= new BufferedReader(new FileReader(path))) {
            String line = file.readLine();
            System.out.println(line + " taille : " + line.length());
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";

    }
}
