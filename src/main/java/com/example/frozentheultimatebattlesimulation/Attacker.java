package com.example.frozentheultimatebattlesimulation;

public class Attacker extends Character{

    protected int Strength;
    public Attacker(int Hp, int IceResistance, int MoveRange, int Strength) {
        super(Hp,IceResistance, MoveRange);
        this.Strength=Strength;

    }

    static protected void Attack(Attacker napastnik1, Attacker napastnik2)
    {
        if(napastnik1.Strength> napastnik2.Strength) napastnik2=null;
        else napastnik1=null;
    }
}
