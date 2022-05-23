package com.example.frozentheultimatebattlesimulation;

public class Attacker extends Character{
    public Attacker(int Hp, int MoveRange, int IceResistance) {
        super(Hp, MoveRange, IceResistance);
    }

    static protected void Attack(Attacker napastnik1, Attacker napastnik2)
    {
        if(napastnik1.Strength> napastnik2.Strength) napastnik2=null;
        else napastnik1=null;
    }
}
