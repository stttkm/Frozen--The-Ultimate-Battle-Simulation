package com.example.frozentheultimatebattlesimulation;

public class Person extends Attacker{

    boolean hasHorse;

    public Person(int Hp, int MoveRange, int IceResistance) {
        super(Hp, MoveRange, IceResistance);
        hasHorse=false;
        if(hasHorse) MoveRange+=1;
    }

}
