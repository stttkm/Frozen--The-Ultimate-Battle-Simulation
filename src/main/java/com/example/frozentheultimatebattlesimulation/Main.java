package com.example.frozentheultimatebattlesimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    static int mapSize = 10;
    static int hansArmySize = 25;
    static int elsasArmySize=25;
    static ArrayList turns = new ArrayList<Turn>();
    public static void main(String[] args)  {launch(args);}



    @Override
    public void start(Stage primaryStage) throws IOException {
        // Zaczynamy od okna ustawień
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        primaryStage.setTitle("Frozen: The Ultimate Battle Simulation");
        primaryStage.setScene(new Scene(root));

        //dodajemy ikonkę !!
        Image icon  = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/O.png ");
        primaryStage.getIcons().add(icon);

        primaryStage.show(); // Show Yourself
    }
}