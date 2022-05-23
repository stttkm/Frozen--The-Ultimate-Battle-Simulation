package com.example.frozentheultimatebattlesimulation;

public class Weapon {
    protected int Power;
    protected int Exhaustion;

    public Weapon(int Power, int Exhaustion)
    {
        this.Power=Power;
        this.Exhaustion=Exhaustion;
    }
    public Weapon()
    {
        this(5,3);
    }

    public Boolean CanWeaponBeUsed()
    {
        if(Exhaustion>0) return true;
        return false;
    }

}
