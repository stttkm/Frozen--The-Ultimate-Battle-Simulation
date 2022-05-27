package com.example.frozentheultimatebattlesimulation;

public class Weapon {
    protected int Power;
    protected int exhaustion;

    public Weapon(int Power, int Exhaustion)
    {
        this.Power=Power;
        this.exhaustion =Exhaustion;
    }
    public Weapon()
    {
        this(5,3);
    }

    public Boolean canWeaponBeUsed()
    {
        if(exhaustion >0) return true;
        return false;
    }

}
