package com.example.frozentheultimatebattlesimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasa Main zawiera statyczne pola (mapSize, hansArmySize, elsasArmySize), które są parametrami rozgrywającej się symulacji
 */
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


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.F11.equals(event.getCode())) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });


        primaryStage.show(); // Show Yourself
    }
}