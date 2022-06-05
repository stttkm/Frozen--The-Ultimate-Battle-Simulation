package com.example.frozentheultimatebattlesimulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.turns;

/**
 * Klasa Attacker dziedziczy po klasie Character.
 */
public class Attacker extends Character{

    protected int strength;

    static String [] AttackerType={"Person", "Snowman", "Wolf"}; //ty wiesz, że istniej getClass().getSimpleName() ???
    public String attackertype;
    boolean upgraded = false;

    /**
     * Class Constructor
     */
    public Attacker()
    {
        super();
        strength=1;
    }
    /**
     * Class constructor specifying value of
     * @param strength siła Attackera- wielkość przydatna w walce
     */
    public Attacker(int strength)
    {
        super();
        this.strength=strength;
    }

    /**
     * Class constructor specifying values of: @param Hp @param IceResistance @param MoveRange @param strength
     */
    public Attacker(int Hp, int IceResistance, int MoveRange, int strength) {
        super(Hp,IceResistance, MoveRange);
        this.strength=strength;

    }



    /**
     * Metoda sprawdza, czy dokonano ataku.
     * @param enemiesInRange lista wrogów (obiektów klas dziedziczących po klasie Attacker, ale nienależących do tej samej klasy co konkretny obiket wywołujący metodę)
     * @return Czy dokonano ataku,czy nie
     */
    protected boolean attack(ArrayList<Point> enemiesInRange){
        Random random = new Random();
        if(enemiesInRange.size()!=0) {
            Point target = enemiesInRange.get(random.nextInt(enemiesInRange.size()));
            switch (((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].occupiedBy) {
                case "Soldier" -> {
                    int soldierIndex = ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].indexOfOccupiedBy;
                    this.hp = this.hp - ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).strength;
                    ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).hp -= this.strength;
                    Turn.notify(this.getClass().getSimpleName() + " #" + this.id+ " ["+ this.x + ","+this.y+"] attacks soldier #"  + ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).id + " [" + ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).x + "," + ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).y + "]");
                    if (((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).hp < 1) {
                        Turn.notify("Soldier #" + ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).id + " [" + ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).x +"," + ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).y + "] died!");
                        ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).die();
                    }
                }
                case "Snowman" -> {
                    int snowmanIndex = ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].indexOfOccupiedBy;
                    this.hp = this.hp - ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).strength;
                    ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).hp -= this.strength;
                    Turn.notify(this.getClass().getSimpleName() + " #" + this.id+ " ["+ this.x + ","+this.y+"] attacks snowman #"  + ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).id + " [" + ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).x+","+((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).y+"]");
                    if (((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).hp < 1) {
                        Turn.notify("Snowman #" + ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).id +" ["+ ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).x + "," +((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).y +"] melted!");
                        ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).die();
                    }
                }
                case "IceBreaker" -> {
                    int iceBreakerIndex = ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].indexOfOccupiedBy;
                    ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).hp -= this.strength;
                    Turn.notify(this.getClass().getSimpleName() + " #" + this.id+ " ["+ this.x + ","+this.y+"] attacks ice breaker #"  + ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).id + " [" + ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).x+"," + ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).y + "]");
                    if (((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).hp < 1) {
                        Turn.notify("Ice breaker #" + ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).id + " [" + ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).x +"," + ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).y+"] died!");
                        ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).die();
                    }
                }
                case "Anna" -> {
                    this.hp = this.hp - (((Turn) turns.get(turns.size() - 1)).anna.hp);
                    ((Turn) turns.get(turns.size() - 1)).anna.hp -= this.strength;
                    Turn.notify(this.getClass().getSimpleName() + " #" + this.id+ " ["+ this.x + ","+this.y+"] attacks Anna!");
                    if ((((Turn) turns.get(turns.size() - 1)).anna.hp < 1)) {
                        Turn.notify("Ravenous wolves devoured Anna voraciously!");
                        ((Turn) turns.get(turns.size() - 1)).kill(((Turn) turns.get(turns.size() - 1)).anna);
                    }
                }
                case "Kristoff" -> {
                    this.hp = this.hp - (((Turn) turns.get(turns.size() - 1)).kristoff.hp);
                    ((Turn) turns.get(turns.size() - 1)).kristoff.hp -= this.strength;
                    Turn.notify(this.getClass().getSimpleName() + " #" + this.id+ " ["+ this.x + ","+this.y+"] attacks Kristoff!");
                    if ((((Turn) turns.get(turns.size() - 1)).kristoff.hp < 1)) {
                        Turn.notify("Wolves knocked Kristoff off, but life goes on.");
                        ((Turn) turns.get(turns.size() - 1)).kill(((Turn) turns.get(turns.size() - 1)).kristoff);
                    }
                }
                case "Hans" -> {
                    this.hp = this.hp - (((Turn) turns.get(turns.size() - 1)).hans.hp);
                    ((Turn) turns.get(turns.size() - 1)).hans.hp -= this.strength;
                    Turn.notify(this.getClass().getSimpleName() + " #" + this.id+ " ["+ this.x + ","+this.y+"] attacks Hans!");
                    if ((((Turn) turns.get(turns.size() - 1)).hans.hp < 1)) {
                        Turn.notify("Wolves eliminated the greatest danger to the kingdom of Arendell pro bono publico - Hans died, dead!");
                        ((Turn) turns.get(turns.size() - 1)).kill(((Turn) turns.get(turns.size() - 1)).hans);

                    }
                }
            }



            return true; //zrobione
        }

        return false; //niezrobione
    }

    /*
    protected void levelUp()
    {
        hp = (int) Math.floor(hp *1.5);
        strength= (int) Math.floor(strength*1.5);
        MoveRange*=2;
    }

     */
}
