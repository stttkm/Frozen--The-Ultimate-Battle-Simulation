package com.example.frozentheultimatebattlesimulation;

import static com.example.frozentheultimatebattlesimulation.Main.turns;

public class Wolf extends Attacker{
    public static int Quantity;
    public static int counter;
    public Wolf()
    {
        super(3);
        counter++;
        IceResistance=0;
        attackertype=AttackerType[2];
        ((Turn) turns.get(0)).map[y][x].indexOfOccupiedBy=((Turn) turns.get(0)).wolves.size();
    }


}
