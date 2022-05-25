package com.example.frozentheultimatebattlesimulation;

public class Soldier extends Person{
    public static int counter;
    private Weapon weapon;
    public Soldier()
    {
        super();
        weapon=new Weapon();
        counter++;
        strength+=weapon.Power;
        attackertype=AttackerType[0];
    }
    @Override
    protected void Attack(Attacker aggressor, Attacker victim)
    {
        if(!weapon.CanWeaponBeUsed()) strength-= weapon.Power;
        if(aggressor.strength> victim.strength) victim=null;
        else aggressor=null;
        weapon.Exhaustion--;
    }
    @Override
    protected void LevelUp()
    {
        hasHorse=true;
    }

}
