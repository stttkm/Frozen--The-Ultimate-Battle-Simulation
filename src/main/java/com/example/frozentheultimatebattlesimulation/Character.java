package com.example.frozentheultimatebattlesimulation;


import javafx.scene.image.Image;
import javafx.stage.Screen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;

/**
 * Klasa Character dziedziczy po klasie Element, implementuje interfejs Cloneable
 */
public class Character extends Element implements Cloneable {
    protected int hp;
    protected int MoveRange;
    protected int fieldReaction;
    int id;
    Image characterImage = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/" + getClass().getSimpleName()+".png", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true);;;

    /**
     * Class Constructor
     * Przypisuje każdej postaci:
     * wskaźnik życia (hp)
     * zakres ruchu (MoveRange)
     * reakcję na typ podłoża (fieldReaction)
     * współrzędne x i y na mapie
     * Przypisane współrzędne nie mogą być już zajęte przez inną postać
     * Zajęcie przez postać miejsca na mapie powoduje zmianę pola isEmpty mapy na false, dodatkowo chcemy przechowywać informację o tym, prze kogo jest zajmowane to Field.
     */
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

    /**
     * Class Constructor specyfying values
     * @param Hp wskaźnik życia
     * @param MoveRange Zakres ruchu
     * @param fieldReaction Liczba całkowita informująca o reakcji na podłoże
     */

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

    /**
     * Metoda ma na celu wykonanie ruchu przez postać
     * @param availableTilesForMovement lista dostęnych miejsc, na które postać może się przemieścić
     * @return true: udało się wykonać ruch, false: wykonanie ruchu było niemożliwe
     */
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

    /**
     * Metoda "leczy" postać
     * Jeśli postać stanie na pole o określonym typie, jej wskaźnik życia się zwiększa.
     */
    protected void Heal()
    {
        if(((Turn) turns.get(turns.size()-1)).map[x][y].type.equals("Geyser")) hp += fieldReaction;
        if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[y][x].type, "Geyser")) hp += fieldReaction;
    }

    /**
     * Metoda wywołująca akcję postaci podczas symulacji.
     * Tworzymy i uzupełniamy listę availableTilesForMovement wykorzystywaną w metodzie protected boolean move(ArrayList<Point> availableTilesForMovement)
     */
    void act(){
        // tutaj wywołamy reakcję na podłoże

        // szukamy gdzie się ruszyć
        ArrayList<Point> availableTilesForMovement = new ArrayList<Point>();
        for(int i = -1*MoveRange; i<=MoveRange;i++) for(int j=-1*MoveRange;j<=MoveRange; j++){
            if(((Turn)turns.get(turns.size()-1)).map[(this.y+i+mapSize)%mapSize][(this.x+j+mapSize)%mapSize].isEmpty &&!((Turn)turns.get(turns.size()-1)).map[(this.y+i+mapSize)%mapSize][(this.x+j+mapSize)%mapSize].type.equals("Water")){
                availableTilesForMovement.add(new Point((this.x+j+mapSize)%mapSize,(this.y+i+mapSize)%mapSize));
            }}

        // losujemy
        Random random = new Random();
        boolean done = false;
        int attempts=0;
        while(!done && attempts<5){
            attempts++;
            if(random.nextInt(1)==0)
                done = this.move(availableTilesForMovement);
        }





    }




}
