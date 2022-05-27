package com.example.frozentheultimatebattlesimulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;

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

        ((Turn) turns.get(0)).map[y][x].occupiedBy=this.getClass().getSimpleName();
        ((Turn) turns.get(0)).map[y][x].indexOfOccupiedBy=((Turn) turns.get(0)).soldiers.size();
    }
    @Override
    void act(){
        // tutaj wywołamy reakcję na podłoże

        // szukamy gdzie się ruszyć
        ArrayList<Point> availableTilesForMovement = new ArrayList<>();
        for(int i = -1*MoveRange; i<=MoveRange;i++) for(int j=-1*MoveRange;j<=MoveRange; j++){
            if(((Turn)turns.get(turns.size()-1)).map[(this.y+i+mapSize)%mapSize][(this.x+j+mapSize)%mapSize].isEmpty && !Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].type, "Water")){
                availableTilesForMovement.add(new Point((this.x+j+mapSize)%mapSize,(this.y+i+mapSize)%mapSize));
            }}

        // szukamy lodu
        ArrayList<Point> enemiesInRange = new ArrayList<>();
        for(int i = -1*MoveRange; i<=MoveRange;i++) for(int j=-1*MoveRange;j<=MoveRange; j++){
            if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].occupiedBy, "Snowman") && ((Snowman)((Turn) turns.get(turns.size() - 1)).snowmen.get(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].indexOfOccupiedBy)).strength< this.hp){
                // szukamy snowmanów do zaatakowania, których siła jest mniejsza niż nasze hp
                enemiesInRange.add(new Point((this.x+j+mapSize)%mapSize,(this.y+i+mapSize)%mapSize));
            }}

        // losujemy
        Random random = new Random();
        boolean done = false;
        int attempts=0;
        while(!done && attempts<5){
            attempts++;
            switch (random.nextInt(2)) {
                case 0 -> done = this.move(availableTilesForMovement);
                case 1 -> {
                    if(this.weapon.canWeaponBeUsed()) {
                        done = this.attack(enemiesInRange);
                        this.weapon.exhaustion--;
                    }
                }
            }
        }




    }
    @Override
    protected void levelUp()
    {
        hasHorse=true;
    }

}
