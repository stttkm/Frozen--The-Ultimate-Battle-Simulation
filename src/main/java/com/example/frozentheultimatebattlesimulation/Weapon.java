package com.example.frozentheultimatebattlesimulation;

public class Weapon {
    protected int
            power;
    protected int exhaustion;

    public Weapon(int Power, int Exhaustion)
    {
        this.power =Power;
        this.exhaustion =Exhaustion;
    }
    public Weapon()
    {
        this(5,3);
    }

    public Boolean canWeaponBeUsed()
    {
         return exhaustion>0;
    }

}
