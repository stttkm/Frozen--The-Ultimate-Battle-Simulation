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
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import static com.example.frozentheultimatebattlesimulation.Pole.*;

public class Main extends Application {
    static int mapSize = 8;
    static int hansArmySize = -1;
    static int elsasArmySize=-1;
    static Pole [][] Mapa;
    static Character [] Postacie;
    static int charactercounter=0;
    public static void main(String[] args)  {

        launch(args);
        Mapa=new Pole [mapSize][mapSize];
        for(int i=0; i<mapSize; i++)
        {
            for(int j=0; j<mapSize; j++)
            {
                Mapa[i][j].X=i;
                Mapa[i][j].Y=j;
            }
        }
        Postacie= new Character[mapSize^2];
        IceQueen Elsa= new IceQueen();
        Postacie[0]=Elsa; charactercounter++;
        Person Anna= new Person();
        Postacie[1]=Anna; charactercounter++;
        Person Kristoff= new Person(true);
        Postacie[2]=Kristoff; charactercounter++;

        for(int i=0; i<hansArmySize; i++)
        {
            Postacie[i+3]=new Soldier();
            charactercounter++;
        }

        for(int i=0; i<elsasArmySize; i++)
        {
            Postacie[i+3+hansArmySize]=new Snowman();
            charactercounter++;
        }

        IceBreaker.Quantity=(mapSize^2-elsasArmySize-hansArmySize-3)/4;
        for(int i=0; i<IceBreaker.Quantity; i++)
        {
            Postacie[i+3+hansArmySize+elsasArmySize]=new IceBreaker();
            charactercounter++;
        }

        Wolf.Quantity=(mapSize^2-elsasArmySize-hansArmySize-3-IceBreaker.Quantity)/4;
        for(int i=0; i<Wolf.Quantity; i++)
        {
            Postacie[i+3+hansArmySize+elsasArmySize+IceBreaker.Quantity]=new Wolf();
            charactercounter++;
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