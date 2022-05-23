package com.example.frozentheultimatebattlesimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static com.example.frozentheultimatebattlesimulation.Pole.*;

public class Main extends Application {
    static int mapSize = 8;
    static int hansArmySize = -1;
    static int elsasArmySize=-1;
    public static void main(String[] args)  {

        launch(args);
        Pole[][] Mapa=new Pole [mapSize][mapSize];
        for(int i=0; i<mapSize; i++)
        {
            for(int j=0; j<mapSize; j++)
            {
                Mapa[i][j].X=i;
                Mapa[i][j].Y=j;
            }

        }
    }



    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        primaryStage.setTitle("Frozen: The Ultimate Battle Simulation");
        primaryStage.setScene(new Scene(root));

        //dodajemy ikonkę !!
        Image icon  = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/O.png ");
        primaryStage.getIcons().add(icon);

        primaryStage.show(); // Show Yourself
    }
}