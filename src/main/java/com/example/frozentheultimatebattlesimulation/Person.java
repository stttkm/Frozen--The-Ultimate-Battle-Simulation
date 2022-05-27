package com.example.frozentheultimatebattlesimulation;

import javafx.scene.image.Image;
import javafx.stage.Screen;

import java.awt.*;
import java.util.ArrayList;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;

public class Person extends Attacker{
    boolean hasHorse;

    public Person()
    {
        super();
        hasHorse=false;
    }
    public Person(boolean hasHorse)
    {
        super();
        this.hasHorse=hasHorse;
    }
    /*
    public Person(int Hp, int MoveRange, int IceResistance, int strength,  boolean hasHorse) {
        super(Hp,MoveRange, IceResistance, strength);
        this.hasHorse=hasHorse;
        if(hasHorse) this.MoveRange=MoveRange+1;
        else this.MoveRange=MoveRange;
    }
     */

    public Person(int Hp, int MoveRange, int IceResistance, int strength,  boolean hasHorse, String whothis) { //dodałem ten konstruktor, by Anna wyglądała na Annę, itd.
        super(Hp,MoveRange, IceResistance, strength);
        this.hasHorse=hasHorse;
        if(hasHorse) this.MoveRange=MoveRange+1;
        else this.MoveRange=MoveRange;
        ((Turn) turns.get(0)).map[y][x].occupiedBy=whothis;
        characterImage = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/" + whothis +".png", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true);
    }



}
