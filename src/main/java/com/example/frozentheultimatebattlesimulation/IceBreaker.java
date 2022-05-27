package com.example.frozentheultimatebattlesimulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;

public class IceBreaker extends Character{
    public static int iceBreakersQuantity; //ilosc lamaczy lodu liczona na podstawie podanej przez uzytkownika mapSize
    private static int iceBreakersCounter=0; //aktulana ilosc lamaczy lodu
    public IceBreaker(int Hp, int MoveRange, int IceResistance) {
        super(Hp, MoveRange, IceResistance);
        iceBreakersCounter++;
        ((Turn) turns.get(0)).map[y][x].indexOfOccupiedBy=((Turn) turns.get(0)).iceBreakers.size();
    }

    public IceBreaker() {
        super();
            iceBreakersCounter++;
    }

    protected boolean crashIce(ArrayList<Point> availableIce)
    {
        Random random = new Random();
        if(availableIce.size()!=0) {
            Point target = availableIce.get(random.nextInt(availableIce.size()));

            ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].changeIntoWater();
            return true; //zrobione
        }
        return false; //niezrobione
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
    ArrayList<Point> availableIce = new ArrayList<>();
    for(int i = -1*MoveRange; i<=MoveRange;i++) for(int j=-1*MoveRange;j<=MoveRange; j++){
        if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].type, "Ice") && !(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].occupiedBy, "IceBreaker")) && !(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].occupiedBy, "IceQueen"))){
            //IceBreaker nie kopie dołku pod innym IceBreakerem, bo wtedy modyfikujemy listę IceBreakerów i nawiedza nas ConcurrentModificationException
            //nie kupuję wersji, w której IceBreaker może zniszczyć lód pod królową lodu, która umie pola na 2. krańcu mapy zamrażać
            availableIce.add(new Point((this.x+j+mapSize)%mapSize,(this.y+i+mapSize)%mapSize));
        }}

        // losujemy
        Random random = new Random();
        boolean done = false;
        int attempts=0;
        while(!done && attempts<5){
            attempts++;
            switch (random.nextInt(2)) {
                case 0 -> done = this.move(availableTilesForMovement);
                case 1 -> done = this.crashIce(availableIce);
            }
        }




    }

}
