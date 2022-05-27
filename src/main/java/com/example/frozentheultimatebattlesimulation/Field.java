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
            ((Turn)Main.turns.get(Main.turns.size()-1)).isGameOver = true;
            System.out.println("Anna utonęła");
            // ustawiamy napisy końcowe na abismal ending
        }

        if(Objects.equals(this.occupiedBy, "Hans")){
            ((Turn)Main.turns.get(Main.turns.size()-1)).isGameOver = true;
            // ustawiamy napisy końcowe na happy ending
            System.out.println("Hans utonął");
        }
        if(Objects.equals(this.occupiedBy, "Kristoff")){
        ((Turn)Main.turns.get(Main.turns.size()-1)).kristoff=null;
            System.out.println("Kristoff się utopił, ale życie toczy się dalej");
    }
        if(Objects.equals(this.occupiedBy, "Snowman")){
            for(int i = this.indexOfOccupiedBy+1; i<((Turn)Main.turns.get(Main.turns.size()-1)).snowmen.size();i++){
                ((Turn)Main.turns.get(Main.turns.size()-1)).map[((Snowman)((Turn)Main.turns.get(Main.turns.size()-1)).snowmen.get(i)).y][((Snowman)((Turn)Main.turns.get(Main.turns.size()-1)).snowmen.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
            }
            ((Turn)Main.turns.get(Main.turns.size()-1)).snowmen.remove(this.indexOfOccupiedBy);
        }
        if(Objects.equals(this.occupiedBy, "IceBreaker")){
            for(int i = this.indexOfOccupiedBy+1; i<((Turn)Main.turns.get(Main.turns.size()-1)).iceBreakers.size();i++){
                ((Turn)Main.turns.get(Main.turns.size()-1)).map[((IceBreaker)((Turn)Main.turns.get(Main.turns.size()-1)).iceBreakers.get(i)).y][((IceBreaker)((Turn)Main.turns.get(Main.turns.size()-1)).iceBreakers.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
            }
            ((Turn)Main.turns.get(Main.turns.size()-1)).iceBreakers.remove(this.indexOfOccupiedBy);
        }
        if(Objects.equals(this.occupiedBy, "Soldier")){
            for(int i = this.indexOfOccupiedBy+1; i<((Turn)Main.turns.get(Main.turns.size()-1)).soldiers.size();i++){
                ((Turn)Main.turns.get(Main.turns.size()-1)).map[((Soldier)((Turn)Main.turns.get(Main.turns.size()-1)).soldiers.get(i)).y][((Soldier)((Turn)Main.turns.get(Main.turns.size()-1)).soldiers.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
            }

            ((Turn)Main.turns.get(Main.turns.size()-1)).soldiers.remove(this.indexOfOccupiedBy);
        }
        if(Objects.equals(this.occupiedBy, "Wolf")){
            for(int i = this.indexOfOccupiedBy+1; i<((Turn)Main.turns.get(Main.turns.size()-1)).wolves.size();i++){
                ((Turn)Main.turns.get(Main.turns.size()-1)).map[((Wolf)((Turn)Main.turns.get(Main.turns.size()-1)).wolves.get(i)).y][((Wolf)((Turn)Main.turns.get(Main.turns.size()-1)).wolves.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
            }
            ((Turn)Main.turns.get(Main.turns.size()-1)).wolves.remove(this.indexOfOccupiedBy);
        }


                this.isEmpty = true;
                this.indexOfOccupiedBy = 0;
                this.occupiedBy = null;
    }
}
