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
           if(X==Postacie[i].X && Y==Postacie[i].Y && attackertype!=Postacie[i].attackertype)
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
