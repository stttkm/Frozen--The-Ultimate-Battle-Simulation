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

<<<<<<< HEAD
    public Attacker(int Hp, int IceResistance, int MoveRange, int strength) {
        super(Hp,IceResistance, MoveRange);
        this.strength=strength;

    }

    static protected void Attack(Attacker aggressor, Attacker victim)
=======

     protected void Attack(Attacker napastnik1, Attacker napastnik2)
>>>>>>> Interior
    {
        if(aggressor.strength> victim.strength) victim=null;
        else aggressor=null;
    }
    protected void LevelUp()
    {
        Hp= (int) Math.floor(Hp*1.5);
        Strength= (int) Math.floor(Strength*1.5);
        MoveRange*=2;
    }
}
