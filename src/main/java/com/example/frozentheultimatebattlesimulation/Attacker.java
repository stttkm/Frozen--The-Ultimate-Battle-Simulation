package com.example.frozentheultimatebattlesimulation;

import static com.example.frozentheultimatebattlesimulation.Main.*;
import static com.example.frozentheultimatebattlesimulation.Main.main;

public class Attacker extends Character{

    protected int Strength;

    static String [] AttackerType={"Person", "Snowman", "Wolf"};
    public String attackertype;

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
