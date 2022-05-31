package com.example.frozentheultimatebattlesimulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;

public class Wolf extends Attacker{
    public static int Quantity;
    public static int counter;
    public Wolf()
    {
        super(3);
        counter++;
        IceResistance=0;
        attackertype=AttackerType[2];
        ((Turn) Main.turns.get(0)).map[y][x].indexOfOccupiedBy=((Turn) Main.turns.get(0)).wolves.size();
    }

    @Override
    void act(){
        // tutaj wywołamy reakcję na podłoże

        // szukamy gdzie się ruszyć i lodu
        ArrayList<Point> availableTilesForMovement = new ArrayList<>();
        ArrayList<Point> enemiesInRange = new ArrayList<>();
        for(int i = -1*MoveRange; i<=MoveRange;i++) for(int j=-1*MoveRange;j<=MoveRange; j++){
            if(((Turn) Main.turns.get(Main.turns.size()-1)).map[(this.y+i+mapSize)%mapSize][(this.x+j+mapSize)%mapSize].isEmpty && !Objects.equals(((Turn) Main.turns.get(Main.turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].type, "Water")){
                availableTilesForMovement.add(new Point((this.x+j+mapSize)%mapSize,(this.y+i+mapSize)%mapSize));
            }
            if(!(((Turn)Main.turns.get(Main.turns.size()-1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].isEmpty && !(Objects.equals(((Turn) Main.turns.get(Main.turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].occupiedBy, "IceQueen")))){
                // szukamy żołnierzy do zaatakowania, których siła jest mniejsza niż nasze hp
                enemiesInRange.add(new Point((this.x+j+mapSize)%mapSize,(this.y+i+mapSize)%mapSize));
            }
        }

        // losujemy
        Random random = new Random();
        boolean done = false;
        int attempts=0;
        while(!done && attempts<5){
            attempts++;
            switch (random.nextInt(2)) {
                case 0 -> done = this.move(availableTilesForMovement);
                case 1 -> done = this.attack(enemiesInRange);
            }
        }




    }
    void die(){
        int index = ((Turn)turns.get(turns.size()-1)).wolves.indexOf(this);
        for (int i = index+ 1; i < ((Turn) Main.turns.get(Main.turns.size() - 1)).wolves.size(); i++) {
            ((Turn) Main.turns.get(Main.turns.size() - 1)).map[((Wolf) ((Turn) Main.turns.get(Main.turns.size() - 1)).wolves.get(i)).y][((Wolf) ((Turn) Main.turns.get(Main.turns.size() - 1)).wolves.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
        }
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].indexOfOccupiedBy =0;
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].occupiedBy = null;
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].isEmpty = true;
        ((Turn) turns.get(turns.size() - 1)).wolves.remove(index);
    }


}
