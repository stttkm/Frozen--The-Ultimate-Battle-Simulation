package com.example.frozentheultimatebattlesimulation;

import javafx.scene.image.Image;
import javafx.stage.Screen;


public class Field extends Element
{  static int[] fieldTypesCount = {0,0,0,0};
    static String[] fieldTypes = {"Ice", "Water", "Earth", "Geyser"};
    Image fieldImage;
    String type;
    boolean isEmpty;

    protected Field(int x, int y)
    {
        SetCoordinates(x,y);
        isEmpty=true;

        //przypisywanie rodzaju pola
        int random = (int) (Math.random() * 99);
        if (random<70) {this.type= fieldTypes[0]; fieldTypesCount[0]++;}
        else if (random>69 && random < 85) {
            if (fieldTypesCount[1]<=0.15*Main.mapSize*Main.mapSize){ // zmieniam to, by nie było sytuacji, że cała mapa to woda,
                this.type= fieldTypes[1];                                                                      // a symulacja nie odpali, bo nie umie usadzić postaci
                fieldTypesCount[1]++;
            } else {
                this.type= fieldTypes[0];
                fieldTypesCount[0]++;
            }
        }
        else if (random>84 && random < 95) {this.type= fieldTypes[2]; fieldTypesCount[2]++;}
        else  {this.type= fieldTypes[3]; fieldTypesCount[3]++;}


        this.fieldImage = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/" +this.type +".jpg", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true);

    }

    public void ChangeIntoIce()
    {
        if(type.equals( fieldTypes[1])) type= fieldTypes[0];
    }
    public void ChangeIntoWater()
    {
        if(type.equals( fieldTypes[0])) type= fieldTypes[1];
    }
}
