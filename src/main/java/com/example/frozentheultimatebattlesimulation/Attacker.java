package com.example.frozentheultimatebattlesimulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.turns;

public class Attacker extends Character{

    protected int strength;

    static String [] AttackerType={"Person", "Snowman", "Wolf"}; //ty wiesz, że istniej getClass().getSimpleName() ???
    public String attackertype;

    public Attacker()
    {
        super();
        strength=1;
    }
    public Attacker(int strength)
    {
        super();
        this.strength=strength;
    }


    public Attacker(int Hp, int IceResistance, int MoveRange, int strength) {
        super(Hp,IceResistance, MoveRange);
        this.strength=strength;

    }




    protected boolean attack(ArrayList<Point> enemiesInRange){
        Random random = new Random();
        if(enemiesInRange.size()!=0) {
            Point target = enemiesInRange.get(random.nextInt(enemiesInRange.size()));
            switch (((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].occupiedBy) {
                case "Soldier" -> {
                    int soldierIndex = ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].indexOfOccupiedBy;
                    this.hp = this.hp - ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).strength;
                    ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).hp -= this.strength;

                    if (((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).hp < 1) {
                        ((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).die();
                    }
                }
                case "Snowman" -> {
                    int snowmanIndex = ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].indexOfOccupiedBy;
                    this.hp = this.hp - ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).strength;
                    ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).hp -= this.strength;
                    if (((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).hp < 1) {
                        ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).die();
                    }
                }
                case "IceBreaker" -> {
                    int iceBreakerIndex = ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].indexOfOccupiedBy;
                    ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).hp -= this.strength;
                    if (((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).hp < 1) {
                        ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).die();
                    }
                }
                case "Anna" -> {
                    this.hp = this.hp - (((Turn) turns.get(turns.size() - 1)).anna.hp);
                    ((Turn) turns.get(turns.size() - 1)).anna.hp -= this.strength;
                    if ((((Turn) turns.get(turns.size() - 1)).anna.hp < 1)) {
                        System.out.println("Wilki zjadły Annę");
                        ((Turn) turns.get(turns.size() - 1)).kill(((Turn) turns.get(turns.size() - 1)).anna);
                    }
                }
                case "Kristoff" -> {
                    this.hp = this.hp - (((Turn) turns.get(turns.size() - 1)).kristoff.hp);
                    ((Turn) turns.get(turns.size() - 1)).kristoff.hp -= this.strength;
                    if ((((Turn) turns.get(turns.size() - 1)).kristoff.hp < 1)) {
                        System.out.println("Wilki zjadły Kristoffa, ale życie toczy się dalej");
                        ((Turn) turns.get(turns.size() - 1)).kill(((Turn) turns.get(turns.size() - 1)).kristoff);
                    }
                }
                case "Hans" -> {
                    this.hp = this.hp - (((Turn) turns.get(turns.size() - 1)).hans.hp);
                    ((Turn) turns.get(turns.size() - 1)).hans.hp -= this.strength;
                    if ((((Turn) turns.get(turns.size() - 1)).hans.hp < 1)) {
                        System.out.println("Wilki zjadły Hansa");
                        ((Turn) turns.get(turns.size() - 1)).kill(((Turn) turns.get(turns.size() - 1)).hans);

                    }
                }
            }



            return true; //zrobione
        }

        return false; //niezrobione
    }
    protected void levelUp()
    {
        hp = (int) Math.floor(hp *1.5);
        strength= (int) Math.floor(strength*1.5);
        MoveRange*=2;
    }
}
