package com.example.frozentheultimatebattlesimulation;

import static com.example.frozentheultimatebattlesimulation.Main.turns;

public class Snowman extends Attacker {
    public static int Quantity;
    public static int counter;
    public Snowman()
    {
        super();
        counter++;
    }
    @Override
    protected void IceReaction()
    {
        if(((Turn) turns.get(turns.size()-1)).map[x][y].type=="Ice") Hp+=IceResistance;
    }

    @Override
    protected void Heal()
    {
        if(((Turn) turns.get(turns.size()-1)).map[x][y].type=="Geyser") Hp-=IceResistance;
    }
}
