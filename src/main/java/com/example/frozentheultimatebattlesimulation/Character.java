package com.example.frozentheultimatebattlesimulation;


import javafx.scene.image.Image;
import javafx.stage.Screen;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.*;
import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.*;

public class Character extends Element implements Cloneable {
    protected int Hp;
    protected int MoveRange;
    protected int IceResistance;
    Image characterImage = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/" + getClass().getSimpleName()+".png", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true);;;

    public Character()
    {
        Hp=2*mapSize;
        MoveRange=1;
        IceResistance=0;
        int x;
        int y;
        do
        {
            x = (int)Math.floor(Math.random()*mapSize);
            y = (int)Math.floor(Math.random()*mapSize);
        }while(!(((Turn) turns.get(0)).map[y][x].isEmpty) || ((Turn) turns.get(0)).map[y][x].type=="Water"); //nie chcemy falstartu i wrzucania ludzi do wody
        SetCoordinates(x,y);
        ((Turn) turns.get(0)).map[y][x].isEmpty=false;

    }

    public Character(int Hp, int MoveRange, int IceResistance)
    {
        this.Hp=Hp;
        this.MoveRange=MoveRange;
        this.IceResistance=IceResistance;
        int x;
        int y;
        do
        {
            x = (int)Math.floor(Math.random()*mapSize);
            y = (int)Math.floor(Math.random()*mapSize);
        }while(!(((Turn) turns.get(0)).map[y][x].isEmpty) || ((Turn) turns.get(0)).map[y][x].type=="Water"); //nie chcemy falstartu i wrzucania ludzi do wody
        SetCoordinates(x,y);
        ((Turn) turns.get(0)).map[y][x].isEmpty=false;

    }

    protected void move()
    {
        if(MoveRange!=0){
            String[] directions ={"Left", "Right", "Top", "Bottom"};
            String direction;
            do
            {
                Random random = new Random();
                direction=directions[random.nextInt(4)];

                this.x= switch(direction)
                        {
                            case "Left":
                                yield (((this.x-MoveRange)+mapSize)%mapSize);
                            case "Right":
                                yield (((this.x+MoveRange)+mapSize)%mapSize);
                            default: yield this.x;
                        };
                this.y= switch(direction)
                        {
                            case "Top": yield (((this.y+MoveRange)+mapSize)%mapSize);
                            case "Bottom": yield (((this.y-MoveRange)+mapSize)%mapSize);
                            default: yield this.y;
                        };
            }while(!(((Turn) turns.get(turns.size()-1)).map[y][x].isEmpty)); //trzeba dodać, że pole zostało zwolnione, a nowe pole zajęte
            //ta funkcja umożliwia teleporty xDDDD

        }

    }
    protected boolean Drown()
    {
        if(((Turn)Main.turns.get(Main.turns.size()-1)).map[y][x].type.equals("Water")) return true;
        return false;
    }

    protected void IceReaction()
    {
        if(((Turn) turns.get(turns.size()-1)).map[x][y].type=="Ice") Hp-=IceResistance;

        if(((Turn)Main.turns.get(Main.turns.size()-1)).map[y][x].type.equals("Ice")) Hp-=IceResistance;

    }

    protected void Heal()
    {

        if(((Turn) turns.get(turns.size()-1)).map[x][y].type=="Geyser") Hp+=IceResistance;
        if(((Turn)Main.turns.get(Main.turns.size()-1)).map[y][x].type.equals("Geyser")) {
            Hp += IceResistance;
        };
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
