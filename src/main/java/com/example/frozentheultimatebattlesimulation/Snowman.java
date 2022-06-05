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
 * Klasa Snowman dziedziczy po klasie Attacker
 * Obiekty tej klasy to żołnierze Armii Elsy
 */
public class Snowman extends Attacker {
    public static int counter;
    static Text[]
            idImages;

    /**
     * Class Constructor
     */
    public Snowman()
    {
        super();
        counter++;
        attackertype=AttackerType[1];
        fieldReaction = 2;
        ((Turn) turns.get(0)).map[y][x].indexOfOccupiedBy=((Turn) turns.get(0)).snowmen.size();
        id = ((Turn) turns.get(0)).snowmen.size();
        ((Turn) turns.get(0)).map[y][x].idOfOccupiedBy=this.id;
    }

    /**
     * Metoda obsługująca ruch w turze obiektu klasy Snowman
     * Tworzy lsitę enemiesInRange- wrogów (obiektów klas Person i Wolf) znajdujących się w zasięgu ruchu
     * Losuje jaką metodę Snowman wykona w danej turze: ruszyć się czy atakować
     */
    @Override
    void act(){
        // tutaj wywołamy reakcję na podłoże

        // szukamy lodu i gdzie się ruszyć
        ArrayList<Point> enemiesInRange = new ArrayList<>();
        ArrayList<Point> availableTilesForMovement = new ArrayList<>();
        for(int i = -1*MoveRange; i<=MoveRange;i++) for(int j=-1*MoveRange;j<=MoveRange; j++){
            if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].occupiedBy, "Soldier") && ((Soldier)((Turn) turns.get(turns.size() - 1)).soldiers.get(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].indexOfOccupiedBy)).strength< this.hp){
                // szukamy żołnierzy do zaatakowania, których siła jest mniejsza niż nasze hp
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
                case 1 -> done = this.attack(enemiesInRange);
            }
        }




    }

    /**
     * Obsługa śmierci obiektu Snowman, zwolnienie zajmowanego przez obiekt Field.
     */
    void die(){
        int snowmanIndex = ((Turn)turns.get(turns.size()-1)).snowmen.indexOf(this);
        for (int i = snowmanIndex+ 1; i < ((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.size(); i++) {
            ((Turn) Main.turns.get(Main.turns.size() - 1)).map[((Snowman) ((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(i)).y][((Snowman) ((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(i)).x].indexOfOccupiedBy--; //update'ujemy indexy
        }
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].indexOfOccupiedBy =0;
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].occupiedBy = null;
        ((Turn) turns.get(turns.size() - 1)).map[this.y][this.x].isEmpty = true;
        ((Turn) turns.get(turns.size() - 1)).snowmen.remove(snowmanIndex);
    }

    /**
     * Wyświetlenie id obiektu na zajmowanym przez niego polu
     */
    static void generateIdImages(){
        idImages = new Text[Main.elsasArmySize];
        int fontSize = (int) ((Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize) *0.65);

        for(int i=0; i<Main.elsasArmySize; i++){
            Text id = new Text(Integer.toString(i));
            id.setStyle("-fx-font: "+fontSize+" impact; -fx-stroke: #000000; -fx-stroke-width: 1; -fx-fill: #ffffff;");
            idImages[i] = id;
        }
    }

    /**
     * Ulepszenie Snowman, jest wynikiem działania metody boolean promote(ArrayList<Integer> snowmenToBeUpgraded) klasy IceQueen
     */
    void upgrade(){
        this.upgraded = true;
        this.hp +=10;
        this.fieldReaction = 1;
        this.strength +=3;
        this.characterImage = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Yeti.png", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true);
    }
}
