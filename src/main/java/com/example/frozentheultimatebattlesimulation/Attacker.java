package com.example.frozentheultimatebattlesimulation;

import static com.example.frozentheultimatebattlesimulation.Main.*;
import static com.example.frozentheultimatebattlesimulation.Main.main;

public class Attacker extends Character{

    protected int strength;

    static String [] AttackerType={"Person", "Snowman", "Wolf"};
    public String attackertype;

    public Attacker()
    {
        super();
        strength=1;
    }
    public Attacker(int strength)
    {
        super();
        this.strength=strength;
    }

/*
    public Attacker(int Hp, int IceResistance, int MoveRange, int strength) {
        super(Hp,IceResistance, MoveRange);
        this.strength=strength;

    }

 */




    protected void Attack(Attacker aggressor, Attacker victim)

    {
        if(aggressor.strength> victim.strength) victim=null;
        else aggressor=null;
    }
    protected void LevelUp()
    {
        Hp= (int) Math.floor(Hp*1.5);
        strength= (int) Math.floor(strength*1.5);
        MoveRange*=2;
    }
}
