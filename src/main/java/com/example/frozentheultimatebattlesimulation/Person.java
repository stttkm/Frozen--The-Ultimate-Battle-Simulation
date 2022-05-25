package com.example.frozentheultimatebattlesimulation;

public class Person extends Attacker{

    boolean hasHorse;

    public Person()
    {
        super();
        hasHorse=false;
    }
    public Person(boolean hasHorse)
    {
        super();
        this.hasHorse=hasHorse;
    }
    /*
    public Person(int Hp, int MoveRange, int IceResistance, int Strength,  boolean hasHorse) {
        super(Hp,MoveRange, IceResistance, Strength);
        this.hasHorse=hasHorse;
        if(hasHorse) this.MoveRange=MoveRange+1;
        else this.MoveRange=MoveRange;
    }
     */

}
