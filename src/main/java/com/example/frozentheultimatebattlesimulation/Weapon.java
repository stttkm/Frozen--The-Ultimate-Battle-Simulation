package com.example.frozentheultimatebattlesimulation;

/**
 * Klasa Weapon
 * Obiekty klasy Weapon są polami w klasie Soldier
 */
public class Weapon {

    protected int power;
    protected int exhaustion;

    /**
     * Class Contructor
     * @param Power moc danej broni
     * @param Exhaustion zużycie danej broni (liczba całkowita informująca, ile razy można użyć daną broń)
     */
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
