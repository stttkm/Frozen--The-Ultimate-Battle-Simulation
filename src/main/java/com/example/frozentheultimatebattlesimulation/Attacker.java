package com.example.frozentheultimatebattlesimulation;

public class Attacker extends Character{

    protected int strength;

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

    public Attacker(int Hp, int IceResistance, int MoveRange, int strength) {
        super(Hp,IceResistance, MoveRange);
        this.strength=strength;

    }

    static protected void Attack(Attacker aggressor, Attacker victim)
    {
        if(aggressor.strength> victim.strength) victim=null;
        else aggressor=null;
    }
}
