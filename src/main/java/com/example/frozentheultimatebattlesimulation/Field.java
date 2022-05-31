package com.example.frozentheultimatebattlesimulation;

import javafx.scene.image.Image;
import javafx.stage.Screen;

import java.util.Objects;
import java.util.Random;

public class Field extends Element implements Cloneable
{  static int[] fieldTypesCount = {0,0,0,0};
    static String[] fieldTypes = {"Ice", "Water", "Earth", "Geyser"};
    static Image[] fieldImages = {new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Ice.jpg", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true),
            new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Water.jpg", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true),
            new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Earth.jpg", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true),
            new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Geyser.jpg", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true)};
    String type;
    boolean isEmpty;
    public String occupiedBy;
    int indexOfOccupiedBy;

    protected Field(int x, int y)
    {
        setCoordinates(x,y);
        isEmpty=true;


        //przypisywanie rodzaju pola
        Random random = new Random();
        int randomNum = random.nextInt(100);
        if (randomNum<70) {this.type= fieldTypes[0];  fieldTypesCount[0]++;}
        if (randomNum>69 && randomNum < 85) {
            if (fieldTypesCount[1]<=0.15*Main.mapSize*Main.mapSize){ // zmieniam to, by nie było sytuacji, że cała mapa to woda,
                this.type= fieldTypes[1];                                                                   // a symulacja nie odpali, bo nie umie usadzić postaci
                fieldTypesCount[1]++;
            } else {
                this.type= fieldTypes[0];
                fieldTypesCount[0]++;
            }
        }
        if (randomNum>84 && randomNum < 95) {this.type= fieldTypes[2]; fieldTypesCount[2]++;}
        if (randomNum>94) {this.type= fieldTypes[3]; fieldTypesCount[3]++;}

    }










    public void changeIntoIce()
    {
        type= fieldTypes[0];
    }
    public void changeIntoWater()
    {
                this.type = fieldTypes[1];

        if(Objects.equals(this.occupiedBy, "Anna")){
            System.out.println("Anna utonęła");
            ((Turn)Main.turns.get(Main.turns.size()-1)).kill(((Turn)Main.turns.get(Main.turns.size()-1)).anna);
            // ustawiamy napisy końcowe na abismal ending
        }

        if(Objects.equals(this.occupiedBy, "Hans")){
            System.out.println("Hans utonął");
            ((Turn)Main.turns.get(Main.turns.size()-1)).kill(((Turn)Main.turns.get(Main.turns.size()-1)).hans);
            // ustawiamy napisy końcowe na happy ending
        }
        if(Objects.equals(this.occupiedBy, "Kristoff")){
            System.out.println("Kristoff się utopił, ale życie toczy się dalej");
            ((Turn)Main.turns.get(Main.turns.size()-1)).kill(((Turn)Main.turns.get(Main.turns.size()-1)).kristoff);

    }
        if(Objects.equals(this.occupiedBy, "Snowman")){
            ((Snowman)((Turn)Main.turns.get(Main.turns.size()-1)).snowmen.get(this.indexOfOccupiedBy)).die();

        }
        if(Objects.equals(this.occupiedBy, "IceBreaker")){
            ((IceBreaker)((Turn)Main.turns.get(Main.turns.size()-1)).iceBreakers.get(this.indexOfOccupiedBy)).die();
        }
        if(Objects.equals(this.occupiedBy, "Soldier")){
            ((Soldier)((Turn)Main.turns.get(Main.turns.size()-1)).soldiers.get(this.indexOfOccupiedBy)).die();
        }
        if(Objects.equals(this.occupiedBy, "Wolf")){
            ((Wolf)((Turn)Main.turns.get(Main.turns.size()-1)).wolves.get(this.indexOfOccupiedBy)).die();
        }


                this.isEmpty = true;
                this.indexOfOccupiedBy = 0;
                this.occupiedBy = null;
    }
}
