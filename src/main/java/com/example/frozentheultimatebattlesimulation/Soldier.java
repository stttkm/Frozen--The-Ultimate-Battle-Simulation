package com.example.frozentheultimatebattlesimulation;

import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;
/**
 * Klasa Soldier dziedziczy po klasie Person
 * Obiekty tej klasy to żołnierze Armii Hansa
 */
public class Soldier extends Person{
    public static int counter;
    private Weapon weapon;
    static Text[] idImages;
    int xpPoints = 0;

    /**
     * Class Constructor
     */
    public Soldier()
    {
        super();
        weapon=new Weapon();
        counter++;
        fieldReaction = 2;
        strength+=weapon.power;
        attackertype=AttackerType[0];

        ((Turn) turns.get(0)).map[y][x].occupiedBy=this.getClass().getSimpleName();
        ((Turn) turns.get(0)).map[y][x].indexOfOccupiedBy=((Turn) turns.get(0)).soldiers.size();
        id = ((Turn) turns.get(0)).soldiers.size();
        ((Turn) turns.get(0)).map[y][x].idOfOccupiedBy=this.id;
    }
    @Override
    void act(){
        if(!weapon.canWeaponBeUsed()) this.strength-=this.weapon.power; // broń się skończyła

        // szukamy gdzie się ruszyć i lodu
        ArrayList<Point> availableTilesForMovement = new ArrayList<>();
        ArrayList<Point> enemiesInRange = new ArrayList<>();
        for(int i = -1*MoveRange; i<=MoveRange;i++) for(int j=-1*MoveRange;j<=MoveRange; j++){
            if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].occupiedBy, "Snowman") && ((Snowman)((Turn) turns.get(turns.size() - 1)).snowmen.get(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].indexOfOccupiedBy)).strength< this.hp){
                // szukamy snowmanów do zaatakowania, których siła jest mniejsza niż nasze hp
                enemiesInRange.add(new Point((this.x+j+mapSize)%mapSize,(this.y+i+mapSize)%mapSize));
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
                case 1 -> {
                    if(this.weapon.canWeaponBeUsed()) {
                        done = this.attack(enemiesInRange);
                        if(done){
                            this.weapon.exhaustion--;
                            this.xpPoints++;
                            if (this.xpPoints == 3){
                                this.upgrade();
                            }
                        }

                    }
                }
            }
        }




    }

    void die(){
    int index = ((Turn)turns.get(turns.size()-1)).soldiers.indexOf(this);
        for (int i = index+ 1; i < ((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.size(); i++) {
            ((Turn) Main.turns.get(Main.turns.size() - 1)).map[((Soldier) ((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(i)).y][((Soldier) ((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
        }
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].indexOfOccupiedBy =0;
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].occupiedBy = null;
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].isEmpty = true;
        ((Turn) turns.get(turns.size() - 1)).soldiers.remove(index);
    }


        static void generateIdImages(){
            idImages = new Text[Main.hansArmySize];
            int fontSize = (int) ((Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize) *0.65);

            for(int i=0; i<Main.hansArmySize; i++){
            Text id = new Text(Integer.toString(i));
            id.setStyle("-fx-font: "+fontSize+" impact; -fx-stroke: white; -fx-stroke-width: 1; -fx-fill: black;");
            idImages[i] = id;
        }
    }
    void upgrade(){
        upgraded = true;
        hp +=10;
        fieldReaction = 1;
        strength +=3;
        characterImage = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Seargent.png", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true);
    }

}



