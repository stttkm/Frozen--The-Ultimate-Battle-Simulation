package com.example.frozentheultimatebattlesimulation;


import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Pole.*;
import static com.example.frozentheultimatebattlesimulation.Main.*;
import static com.example.frozentheultimatebattlesimulation.Main.main;

public class Character extends Element {
    protected int Hp;
    protected int MoveRange;
    public int Strength;
    protected int IceResistance;

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
        }while(!Mapa[x][y].isEmpty);
        SetCoordinates(x,y);
        Mapa[x][y].isEmpty=false;

    }

    protected void Move()
    {
        if(MoveRange!=0){
            String[] kierunki ={"Lewy", "Prawy", "Gora", "Dol"};
            int random_int= (int)Math.floor(Math.random()*4);
            String kierunek;
            int x=X;
            int y=Y;
            do
            {
                kierunek=kierunki[random_int];
                x= switch(kierunek)
                        {
                            case "Lewy": yield x=(x-MoveRange)%mapSize;
                            case "Prawy": yield x=(x+MoveRange)%mapSize;
                            default: yield x;
                        };
                y= switch(kierunek)
                        {
                            case "Gora": yield y=(y+MoveRange)%mapSize;
                            case "Dol": yield y=(y-MoveRange)%mapSize;
                            default: yield y;
                        };
            }while(!Mapa[x][y].isEmpty);
            X=x;
            Y=y;

        }

    }

    protected void IceReaction()
    {
        Hp-=IceResistance;
    }

}