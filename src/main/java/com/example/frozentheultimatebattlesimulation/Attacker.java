package com.example.frozentheultimatebattlesimulation;

import static com.example.frozentheultimatebattlesimulation.Main.*;
import static com.example.frozentheultimatebattlesimulation.Main.main;

public class Attacker extends Character{

    protected int Strength;


    public Attacker()
    {
        super();
        Strength=1;
    }
    public Attacker(int Strength)
    {
        super();
        this.Strength=Strength;
    }

    public Attacker(int Hp, int IceResistance, int MoveRange, int Strength) {
        super(Hp,IceResistance, MoveRange);
        this.Strength=Strength;

    }

    protected Attacker radar()
    {
        for(int i=0; i<charactercounter; i++)
        {
           if((X-MoveRange)%mapSize<=Postacie[i].X && Postacie[i].X <=(X+MoveRange)%mapSize && (Y-MoveRange)%mapSize<=Postacie[i].Y && Postacie[i].Y<=(Y+MoveRange)%mapSize && attackertype!=Postacie[i].attackertype)
               return (Attacker)Postacie[i];
       }
       return null;

    }


     protected void Attack()
    {
        if(Strength> radar().Strength)
        {
            Hp+= radar().Hp;
            radar().Hp-=Strength;
        }

    }


    protected void LevelUp()
    {
        Hp= (int) Math.floor(Hp*1.5);
        Strength= (int) Math.floor(Strength*1.5);
        MoveRange*=2;
    }
}
