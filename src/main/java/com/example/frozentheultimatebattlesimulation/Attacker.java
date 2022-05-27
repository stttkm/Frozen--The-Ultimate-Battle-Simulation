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
                        for (int i = soldierIndex + 1; i < ((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.size(); i++) {
                            ((Turn) Main.turns.get(Main.turns.size() - 1)).map[((Soldier) ((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(i)).y][((Soldier) ((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
                        }
                        ((Turn) turns.get(turns.size() - 1)).map[((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).y][((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).x].indexOfOccupiedBy =0;
                        ((Turn) turns.get(turns.size() - 1)).map[((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).y][((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).x].occupiedBy =null;
                        ((Turn) turns.get(turns.size() - 1)).map[((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).y][((Soldier) ((Turn) turns.get(turns.size() - 1)).soldiers.get(soldierIndex)).x].isEmpty = true;
                        ((Turn) turns.get(turns.size() - 1)).soldiers.remove(soldierIndex);
                    }
                }
                case "Snowman" -> {
                    int snowmanIndex = ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].indexOfOccupiedBy;
                    this.hp = this.hp - ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).strength;
                    ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).hp -= this.strength;
                    if (((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).hp < 1) {
                        for (int i = snowmanIndex + 1; i < ((Turn) turns.get(turns.size() - 1)).snowmen.size(); i++) {
                            ((Turn) Main.turns.get(Main.turns.size() - 1)).map[((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(i)).y][((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
                        }
                        ((Turn) turns.get(turns.size() - 1)).map[((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).y][((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).x].indexOfOccupiedBy =0;
                        ((Turn) turns.get(turns.size() - 1)).map[((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).y][((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).x].occupiedBy =null;
                        ((Turn) turns.get(turns.size() - 1)).map[((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).y][((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(snowmanIndex)).x].isEmpty = true;
                        ((Turn) turns.get(turns.size() - 1)).snowmen.remove(snowmanIndex);
                    }
                }
                case "IceBreaker" -> {
                    int iceBreakerIndex = ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].indexOfOccupiedBy;
                    ((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).hp -= this.strength;
                    if (((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).hp < 1) {
                        for (int i = iceBreakerIndex + 1; i < ((Turn) turns.get(turns.size() - 1)).iceBreakers.size(); i++) {
                            ((Turn) Main.turns.get(Main.turns.size() - 1)).map[((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(i)).y][((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
                        }
                        ((Turn) turns.get(turns.size() - 1)).map[((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).y][((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).x].indexOfOccupiedBy =0;
                        ((Turn) turns.get(turns.size() - 1)).map[((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).y][((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).x].occupiedBy =null;
                        ((Turn) turns.get(turns.size() - 1)).map[((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).y][((IceBreaker) ((Turn) turns.get(turns.size() - 1)).iceBreakers.get(iceBreakerIndex)).x].isEmpty = true;
                        ((Turn) turns.get(turns.size() - 1)).iceBreakers.remove(iceBreakerIndex);
                    }
                }
                case "Anna" -> {
                    this.hp = this.hp - (((Turn) turns.get(turns.size() - 1)).anna.hp);
                    ((Turn) turns.get(turns.size() - 1)).anna.hp -= this.strength;
                    if ((((Turn) turns.get(turns.size() - 1)).anna.hp < 1)) {
                        ((Turn) turns.get(turns.size() - 1)).isGameOver = true;
                        System.out.println("Wilki zjadły Annę");
                    }
                }
                case "IceQueen" -> {
                    this.hp = this.hp - (((Turn) turns.get(turns.size() - 1)).elsa.hp);
                    ((Turn) turns.get(turns.size() - 1)).elsa.hp -= this.strength;
                    if ((((Turn) turns.get(turns.size() - 1)).elsa.hp < 1)) {
                        ((Turn) turns.get(turns.size() - 1)).isGameOver = true;
                        System.out.println("Wilki zjadły królowę śniegu");
                    }
                }
                case "Kristoff" -> {
                    this.hp = this.hp - (((Turn) turns.get(turns.size() - 1)).kristoff.hp);
                    ((Turn) turns.get(turns.size() - 1)).kristoff.hp -= this.strength;
                    if ((((Turn) turns.get(turns.size() - 1)).kristoff.hp < 1)) {
                        ((Turn) turns.get(turns.size() - 1)).map[((Turn) turns.get(turns.size() - 1)).kristoff.y][((Turn) turns.get(turns.size() - 1)).kristoff.x].indexOfOccupiedBy =0;
                        ((Turn) turns.get(turns.size() - 1)).map[((Turn) turns.get(turns.size() - 1)).kristoff.y][((Turn) turns.get(turns.size() - 1)).kristoff.x].occupiedBy =null;
                        ((Turn) turns.get(turns.size() - 1)).map[((Turn) turns.get(turns.size() - 1)).kristoff.y][((Turn) turns.get(turns.size() - 1)).kristoff.x].isEmpty = true;

                        ((Turn) turns.get(turns.size() - 1)).kristoff = null;
                        System.out.println("Wilki zjadły Kristoffa, ale życie toczy się dalej");

                    }
                }
                case "Hans" -> {
                    this.hp = this.hp - (((Turn) turns.get(turns.size() - 1)).hans.hp);
                    ((Turn) turns.get(turns.size() - 1)).hans.hp -= this.strength;
                    if ((((Turn) turns.get(turns.size() - 1)).hans.hp < 1)) {
                        ((Turn) turns.get(turns.size() - 1)).isGameOver = true;
                        System.out.println("Wilki zjadły Hansa");
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
