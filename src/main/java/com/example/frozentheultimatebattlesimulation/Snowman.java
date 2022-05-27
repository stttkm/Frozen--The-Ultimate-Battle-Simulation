package com.example.frozentheultimatebattlesimulation;

import static com.example.frozentheultimatebattlesimulation.Main.turns;

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
        if(((Turn) turns.get(turns.size()-1)).map[x][y].type.equals("Ice")) Hp+=IceResistance;
        if(((Turn)Main.turns.get(Main.turns.size()-1)).map[y][x].type.equals("Ice")) Hp+=IceResistance;
    }

    @Override
    protected void Heal()
    {
        if(((Turn) turns.get(turns.size()-1)).map[x][y].type.equals("Geyser")) Hp-=IceResistance;
        if(((Turn)Main.turns.get(Main.turns.size()-1)).map[y][x].type.equals("Geyser")) Hp-=IceResistance;
    }
}
