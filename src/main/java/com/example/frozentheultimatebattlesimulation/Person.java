package com.example.frozentheultimatebattlesimulation;

import javafx.scene.image.Image;
import javafx.stage.Screen;

import java.util.Objects;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;



public class Person extends Attacker{
    boolean hasHorse;
    String name;

    public Person()
    {
        super();
        hasHorse=false;
    }


    public Person( boolean hasHorse, String whothis) {
        super(2*mapSize,1, 1, 1);
        this.hasHorse=hasHorse;
        if(hasHorse) this.MoveRange=MoveRange+1;
        else this.MoveRange=MoveRange;
        ((Turn) turns.get(0)).map[y][x].occupiedBy=whothis;
        name = whothis;
        characterImage = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/" + whothis +".png", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true);
    }






}
