package com.example.frozentheultimatebattlesimulation;

import static com.example.frozentheultimatebattlesimulation.Main.Mapa;

public class Snowman extends Attacker {
    public static int counter;
    public Snowman()
    {
        super();
        counter++;
        attackertype=AttackerType[1];
    }
    @Override
    protected void IceReaction()
    {
        if(Mapa[X][Y].type=="Ice") Hp+=IceResistance;
    }

    @Override
    protected void Heal()
    {
        if(Mapa[X][Y].type=="Geyser") Hp-=IceResistance;
    }
}
