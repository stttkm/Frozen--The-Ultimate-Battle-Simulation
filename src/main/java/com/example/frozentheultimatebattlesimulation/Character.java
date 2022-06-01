package com.example.frozentheultimatebattlesimulation;


import javafx.scene.image.Image;
import javafx.stage.Screen;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.*;
import java.awt.Point;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Character extends Element implements Cloneable {
    protected int hp;
    protected int MoveRange;
    protected int
            fieldReaction;
    int id;
    Image characterImage = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/" + getClass().getSimpleName()+".png", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true);;;

    public Character()
    {
        hp =2*mapSize;
        MoveRange=1;
        fieldReaction =0;
        int x;
        int y;

        do
        {
            x = (int)Math.floor(Math.random()*mapSize);
            y = (int)Math.floor(Math.random()*mapSize);
        }while(!(((Turn) turns.get(0)).map[y][x].isEmpty) || ((Turn) turns.get(0)).map[y][x].type.equals("Water")); //nie chcemy falstartu i wrzucania ludzi do wody
        setCoordinates(x,y);

        //ustawiamy pole
        ((Turn) turns.get(0)).map[y][x].isEmpty=false;
        ((Turn) turns.get(0)).map[y][x].occupiedBy=this.getClass().getSimpleName();
        ((Turn) turns.get(0)).map[y][x].idOfOccupiedBy=this.id;

    }

    public Character(int Hp, int MoveRange, int fieldReaction)
    {
        this.hp =Hp;
        this.MoveRange=MoveRange;
        this.fieldReaction =
                fieldReaction;
        int x;
        int y;
        do
        {
            x = (int)Math.floor(Math.random()*mapSize);
            y = (int)Math.floor(Math.random()*mapSize);
        }while(!(((Turn) turns.get(0)).map[y][x].isEmpty) || ((Turn) turns.get(0)).map[y][x].type.equals("Water")); //nie chcemy falstartu i wrzucania ludzi do wody
        setCoordinates(x,y);
        ((Turn) turns.get(0)).map[y][x].isEmpty=false;
        ((Turn) turns.get(0)).map[y][x].occupiedBy=this.getClass().getSimpleName();
    }

    protected boolean move(ArrayList<Point> availableTilesForMovement)
    {


            Random random = new Random();
            if(availableTilesForMovement.size()!=0) {
                Point target = availableTilesForMovement.get(random.nextInt(availableTilesForMovement.size()));

                ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].isEmpty = true;
                ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].isEmpty = false;

                ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].occupiedBy = ((Turn) turns.get(turns.size() - 1)).map[y][x].occupiedBy;
                ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].indexOfOccupiedBy = ((Turn) turns.get(turns.size() - 1)).map[y][x].indexOfOccupiedBy;
                ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].idOfOccupiedBy = ((Turn) turns.get(turns.size() - 1)).map[y][x].idOfOccupiedBy;
                ((Turn) turns.get(turns.size() - 1)).map[y][x].occupiedBy = null;
                ((Turn) turns.get(turns.size() - 1)).map[y][x].indexOfOccupiedBy = 0;
                ((Turn) turns.get(turns.size() - 1)).map[y][x].idOfOccupiedBy = 0;

                this.setCoordinates(target.x, target.y);
                return true; //zrobione
            }
        return false; //niezrobione


        }

    protected void Heal()
    {

        if(((Turn) turns.get(turns.size()-1)).map[x][y].type.equals("Geyser")) hp +=
                fieldReaction;
        if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[y][x].type, "Geyser")) {
            hp +=
                    fieldReaction;
        };
    }

    void act(){
        // tutaj wywołamy reakcję na podłoże

        // szukamy gdzie się ruszyć
        ArrayList<Point> availableTilesForMovement = new ArrayList<Point>();
        for(int i = -1*MoveRange; i<=MoveRange;i++) for(int j=-1*MoveRange;j<=MoveRange; j++){
            if(((Turn)turns.get(turns.size()-1)).map[(this.y+i+mapSize)%mapSize][(this.x+j+mapSize)%mapSize].isEmpty &&((Turn)turns.get(turns.size()-1)).map[(this.y+i+mapSize)%mapSize][(this.x+j+mapSize)%mapSize].type!="Water"){
                availableTilesForMovement.add(new Point((this.x+j+mapSize)%mapSize,(this.y+i+mapSize)%mapSize));
            }}

        // losujemy
        Random random = new Random();
        boolean done = false;
        int attempts=0;
        while(!done && attempts<5){
            attempts++;
            switch (random.nextInt(1)) {
                case 0 -> done = this.move(availableTilesForMovement);
            }
        }





    }




}
