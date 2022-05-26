package com.example.frozentheultimatebattlesimulation;


import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.*;

public class Character extends Element {
    protected int Hp;
    protected int MoveRange;
    protected int IceResistance;

    public Character()
    {
        Hp=2*mapSize;
        MoveRange=1;
        IceResistance=1;
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
            String kierunek;
            int x;
            int y;
            int licznik=0;
            do
            {
                x=X;
                y=Y;
                kierunek=kierunki[(int)Math.floor(Math.random()*4)];
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
                licznik++;
            }while(!Mapa[x][y].isEmpty && licznik<4);
            if(licznik!=4)
            {
                X=x;
                Y=y;
            }


        }

    }
    protected boolean Drowned()
    {
        if(Mapa[X][Y].type.equals("Water")) return true;
        return false;
    }

    protected void IceReaction()
    {
        if(Mapa[X][Y].type.equals("Ice")) Hp-=IceResistance;
    }

    protected void Heal()
    {
        if(Mapa[X][Y].type.equals("Geyser")) Hp+=IceResistance;
    }
}
