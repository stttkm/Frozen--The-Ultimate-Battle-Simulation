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
        id = ((Turn) turns.get(0)).iceBreakers.size();
        ((Turn) turns.get(0)).map[y][x].idOfOccupiedBy=this.id;
    }

    public IceBreaker() {
        super();
            iceBreakersCounter++;
        id = ((Turn) turns.get(0)).iceBreakers.size();
        ((Turn) turns.get(0)).map[y][x].idOfOccupiedBy=this.id;
    }

    protected boolean crashIce(ArrayList<Point> availableIce)
    {
        Random random = new Random();
        if(availableIce.size()!=0) {
            Point target = availableIce.get(random.nextInt(availableIce.size()));

            ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].changeIntoWater();
            Turn.notify("Ice breaker #" +this.id + " [" +this.x+"," + this.y +"] crashed ice in the field [" + target.x +"," + target.y +"]!" );
            return true; //zrobione
        }
        return false; //niezrobione
        }
@Override
    void act(){
        // szukamy lodu i gdzie się ruszyć
    ArrayList<Point> availableIce = new ArrayList<>();
    ArrayList<Point> availableTilesForMovement = new ArrayList<>();
    for(int i = -1*MoveRange; i<=MoveRange;i++) for(int j=-1*MoveRange;j<=MoveRange; j++){
        if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].type, "Ice") && !(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].occupiedBy, "IceBreaker")) && !(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].occupiedBy, "IceQueen"))){
            //IceBreaker nie kopie dołku pod innym IceBreakerem, bo wtedy modyfikujemy listę IceBreakerów i nawiedza nas ConcurrentModificationException
            //nie kupuję wersji, w której IceBreaker może zniszczyć lód pod królową lodu, która umie pola na 2. krańcu mapy zamrażać
            availableIce.add(new Point((this.x+j+mapSize)%mapSize,(this.y+i+mapSize)%mapSize));
        }
        if(((Turn)turns.get(turns.size()-1)).map[(this.y+i+mapSize)%mapSize][(this.x+j+mapSize)%mapSize].isEmpty && !Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].type, "Water")){
            availableTilesForMovement.add(new Point((this.x+j+mapSize)%mapSize,(this.y+i+mapSize)%mapSize));
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
                case 1 -> done = this.crashIce(availableIce);
            }
        }




    }


    void die(){
        int index = ((Turn)turns.get(turns.size()-1)).iceBreakers.indexOf(this);
        for (int i = index+ 1; i < ((Turn) Main.turns.get(Main.turns.size() - 1)).iceBreakers.size(); i++) {
            ((Turn) Main.turns.get(Main.turns.size() - 1)).map[((IceBreaker) ((Turn) Main.turns.get(Main.turns.size() - 1)).iceBreakers.get(i)).y][((IceBreaker) ((Turn) Main.turns.get(Main.turns.size() - 1)).iceBreakers.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
        }
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].indexOfOccupiedBy =0;
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].occupiedBy = null;
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].isEmpty = true;
        ((Turn) turns.get(turns.size() - 1)).iceBreakers.remove(index);
    }
}
