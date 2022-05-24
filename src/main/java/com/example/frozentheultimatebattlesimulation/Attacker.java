package com.example.frozentheultimatebattlesimulation;

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

     protected void Attack(Attacker napastnik1, Attacker napastnik2)
    {
        if(napastnik1.Strength> napastnik2.Strength) napastnik2=null;
        else napastnik1=null;
    }
    protected void LevelUp()
    {
        Hp= (int) Math.floor(Hp*1.5);
        Strength= (int) Math.floor(Strength*1.5);
        MoveRange*=2;
    }
}
