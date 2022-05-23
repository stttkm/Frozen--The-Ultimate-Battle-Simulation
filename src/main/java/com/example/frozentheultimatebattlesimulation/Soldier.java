package com.example.frozentheultimatebattlesimulation;

public class Soldier extends Person{
    public static int counter;
    private Weapon weapon;
    public Soldier()
    {
        super();
        weapon=new Weapon();
        counter++;
        Strength+=weapon.Power;
    }
    @Override
    protected void Attack(Attacker napastnik1, Attacker napastnik2)
    {
        if(!weapon.CanWeaponBeUsed()) Strength-= weapon.Power;
        if(napastnik1.Strength> napastnik2.Strength) napastnik2=null;
        else napastnik1=null;
        weapon.Exhaustion--;
    }

}
