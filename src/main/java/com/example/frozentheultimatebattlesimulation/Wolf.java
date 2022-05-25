package com.example.frozentheultimatebattlesimulation;

public class Wolf extends Attacker{
    public static int Quantity;
    public static int counter;
    public Wolf()
    {
        super(3);
        counter++;
        IceResistance=0;
        attackertype=AttackerType[2];
    }


}
