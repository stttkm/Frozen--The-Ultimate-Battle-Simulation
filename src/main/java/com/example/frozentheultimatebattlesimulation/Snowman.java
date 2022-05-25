package com.example.frozentheultimatebattlesimulation;

import static com.example.frozentheultimatebattlesimulation.Main.Mapa;

public class Snowman extends Attacker {
    public static int counter;
    public Snowman()
    {
        super();
        counter++;
        attackertype=AttackerType[1];
        IceResistance=0;
    }
    @Override
    protected void IceReaction()
    {
        if(Mapa[X][Y].type.equals("Ice")) Hp+=IceResistance;
    }

    @Override
    protected void Heal()
    {
        if(Mapa[X][Y].type.equals("Geyser")) Hp-=IceResistance;
    }
}
