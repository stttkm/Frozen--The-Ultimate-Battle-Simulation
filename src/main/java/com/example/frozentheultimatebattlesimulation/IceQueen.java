package com.example.frozentheultimatebattlesimulation;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;

/**
 * Klasa IceQueen dziedziczy po klasie Character
 */
public class IceQueen extends Character{
    /**
     * Class Constructor
     */
    public IceQueen() {
        super();
        MoveRange = 0;
        fieldReaction =0;
    }

    /**
     * Metoda obsługująca zamrażanie przez IceQueen Field o type="Water"
     * @param availableWater lista Field o type= "Water"
     * @return true: IceQueen zamroziła pole false: nie było dostępnych Filed o type="Water"
     */
    private boolean freeze(ArrayList<Point> availableWater)
    {
        Random random = new Random();
        if(availableWater.size()!=0) {
            Point target = availableWater.get(random.nextInt(availableWater.size()));
            ((Turn) turns.get(turns.size() - 1)).map[target.y][target.x].changeIntoIce();
            Turn.notify("Elsa turned water in the field [" + target.x +"," + target.y +"] into ice!" );
            return true; //zrobione
        }
        return false; //niezrobione
    }

    /**
     * Obsługa ruchu IceQueen w turze symulacji
     * Jeśli Anna dotrze do Elsy symulacja kończy się sukcesem
     * Jeśli Hans dotrze do Elsy, Hans wygrywa symulację
     * Tworzenie listy availableWater i losowanie, czy IceQueen zamrozi pole private boolean freeze(ArrayList<Point> availableWater), czy ulepszy element swojej armii (Snowman) boolean promote(ArrayList<Integer> snowmenToBeUpgraded)
     */
    void act(){
        // the cold never bothered me anyway

        // Anna?
        for(int i = -1; i<=1;i++) for(int j=-1;j<=1; j++){
            if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].occupiedBy, "Anna")){
                ((Turn) turns.get(turns.size() - 1)).isGameOver = true;
                Turn.notify("Anna brought Elsa to her senses - from now on Elsa won't go nuclear");
                Simulation.endCredits= "Taylor";
            }
            if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(this.y + i + mapSize) % mapSize][(this.x + j + mapSize) % mapSize].occupiedBy, "Hans")){
                ((Turn) turns.get(turns.size() - 1)).isGameOver = true;
                Turn.notify("Hans did Elsa in  - henceforth he will hold sway over the Kingdom of Arendelle");
                Simulation.endCredits= "Poot";
            }
        }


        // szukamy wody i bałwanów do upgrade'u
        ArrayList<Point> availableWater = new ArrayList<>();
        for(int i = 0; i< mapSize; i++) for(int j = 0; j<mapSize; j++){
            if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[i][j].type, "Water")){
                availableWater.add(new Point(j,i));
            }}
        ArrayList<Integer> snowmenToBeUpgraded = new ArrayList();
        for(int i = 0; i<((Turn) turns.get(turns.size()-1)).snowmen.size(); i++) {
            if(!(((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(i)).upgraded)) {
                snowmenToBeUpgraded.add(i);
            }
        }
        // losujemy
        Random random = new Random();
        boolean done = false;
        int attempts=0;
        while(!done && attempts<5){
            attempts++;
            switch (random.nextInt(2)) {
                case 0 -> done = this.freeze(availableWater);
                case 1 -> done = this.promote(snowmenToBeUpgraded);
            }
        }
    }

    /**
     * Metoda obsługująca ulepszanie przez IceQueen żołnierzy jej armii- obiektu klasy Snowman
     * @param snowmenToBeUpgraded lista obiektów klasy Snowman, których można ulepszyć
     * @return true: IceQueen ulepszyła Snowmana, false: żaden bałwan nie został ulepszony
     */
    boolean promote(ArrayList<Integer> snowmenToBeUpgraded){
        Random random = new Random();
        if(snowmenToBeUpgraded.size()!=0) {
            int indexOfTheChosenOne = random.nextInt(snowmenToBeUpgraded.size());
            ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(indexOfTheChosenOne)).upgrade();
            Turn.notify("Elsa promoted #" + ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(indexOfTheChosenOne)).id + " [" + ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(indexOfTheChosenOne)).x +"," + ((Snowman) ((Turn) turns.get(turns.size() - 1)).snowmen.get(indexOfTheChosenOne)).y +"]!" );
            return true; //zrobione
        }
        return false; //niezrobione
    }


}
