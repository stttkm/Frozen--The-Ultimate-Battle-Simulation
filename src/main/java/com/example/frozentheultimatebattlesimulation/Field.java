package com.example.frozentheultimatebattlesimulation;

import javafx.scene.image.Image;
import javafx.stage.Screen;

import java.util.Objects;
import java.util.Random;

/**
 * Klasa Field
 * Dziedziczy po klasie Element
 * Implementuje Interfejs Cloneable
 */
public class Field extends Element implements Cloneable
{  static int[] fieldTypesCount = {0,0,0,0}; /** Tablica zliczająca wystąpienia poszcególnych typów Field na mapie */
    static String[] fieldTypes = {"Ice", "Water", "Earth", "Geyser"}; /** Dostępne typy Field */
    static Image[] fieldImages = {new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Ice.jpg", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true),
            new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Water.jpg", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true),
            new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Earth.jpg", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true),
            new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Geyser.jpg", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true)};
    String type; /** Typ Field, jedna z wartości z tablicy []fieldTypes */
    boolean isEmpty; /** informacja o tym, czy dane Field jest zajęte */
    public String occupiedBy; /** informacja o tym, przez kogo Field jest zajęte */
    int indexOfOccupiedBy; /** index obiektu Character, który zajmuje Field */
    int idOfOccupiedBy = indexOfOccupiedBy;

    /**
     * Class Contructor
     * @param x współrzędna x Field
     * @param y współrzędna y Field
     * W sposób losowy przypisujemy do Field type (ograniczenie: type "Water" może stanowić max 15% całej mapy)
     */
    protected Field(int x, int y)
    {
        setCoordinates(x,y);
        isEmpty=true;


        //przypisywanie rodzaju pola
        Random random = new Random();
        int randomNum = random.nextInt(100);
        if (randomNum<70) {this.type= fieldTypes[0];  fieldTypesCount[0]++;}
        else if (randomNum < 84) {
            if (fieldTypesCount[1]<=0.15*Main.mapSize*Main.mapSize){ // zmieniam to, by nie było sytuacji, że cała mapa to woda,
                this.type= fieldTypes[1];                                                                   // a symulacja nie odpali, bo nie umie usadzić postaci
                fieldTypesCount[1]++;
            } else {
                this.type= fieldTypes[0];
                fieldTypesCount[0]++;
            }
        }
       else if (randomNum < 95) {this.type= fieldTypes[2]; fieldTypesCount[2]++;}
       else  {this.type= fieldTypes[3]; fieldTypesCount[3]++;}

    }


    /**
     * Metoda zmienia typ Field na "Ice" (zamraża pole)
     */
    public void changeIntoIce()
    {
        type= fieldTypes[0];
    }
    /**
     * Metoda zmienia typ Field na "Water" (rozmraża pole)
     * Jeśli Field było zajęte przez obiekt Character, to obiekt ten tonie
     */
    public void changeIntoWater()
    {
                this.type = fieldTypes[1];

        if(Objects.equals(this.occupiedBy, "Anna")){
            Turn.notify("Sanguine Anna drowned lamentably in the field [" + this.x + ", " + this.y + "]");
            ((Turn)Main.turns.get(Main.turns.size()-1)).kill(((Turn)Main.turns.get(Main.turns.size()-1)).anna);
            // ustawiamy napisy końcowe na abismal ending
        }

        if(Objects.equals(this.occupiedBy, "Hans")){
            Turn.notify("Despicable Hans drowned karmically in the field [" + this.x + ", " + this.y + "]");
            ((Turn)Main.turns.get(Main.turns.size()-1)).kill(((Turn)Main.turns.get(Main.turns.size()-1)).hans);
            // ustawiamy napisy końcowe na happy ending
        }
        if(Objects.equals(this.occupiedBy, "Kristoff")){
            Turn.notify("Alas, loveable Kristoff drowned in the field [" + this.x + ", " + this.y + "]");
            ((Turn)Main.turns.get(Main.turns.size()-1)).kill(((Turn)Main.turns.get(Main.turns.size()-1)).kristoff);

    }
        if(Objects.equals(this.occupiedBy, "Snowman")){
            Turn.notify("Ferocious snowman #" + this.idOfOccupiedBy + " drowned lugubriously in the field [" + this.x + ", " + this.y + "]");
            ((Snowman)((Turn)Main.turns.get(Main.turns.size()-1)).snowmen.get(this.indexOfOccupiedBy)).die();

        }
        if(Objects.equals(this.occupiedBy, "Soldier")){
            Turn.notify("Fearless soldier #" + this.idOfOccupiedBy + " drowned swiftly in the field [" + this.x + ", " + this.y + "]");
            ((Soldier)((Turn)Main.turns.get(Main.turns.size()-1)).soldiers.get(this.indexOfOccupiedBy)).die();
        }
        if(Objects.equals(this.occupiedBy, "Wolf")){
            Turn.notify("Bonhomous wolf #" + this.idOfOccupiedBy + " drowned dolefully in the field [" + this.x + ", " + this.y + "]");
            ((Wolf)((Turn)Main.turns.get(Main.turns.size()-1)).wolves.get(this.indexOfOccupiedBy)).die();
        }

                this.isEmpty = true;
                this.indexOfOccupiedBy = 0;
                this.occupiedBy = null;
    }

    /**
     * Metoda obsługująca reakcję zajmującego Field obiektu Character na type Field
     * W zależności od klasy, do której należy obiekt, obecność na danym Field może leczyć lub ranić  Character
     * Jeśli Person (Hans, Anna, Kristoff, Soldier) stanie na Field o type="Ice" zostaje ona zraniona (hp Person się zmniejsza), jeśli type="Geyser"- zostaje ona leczona
     * Jeśli Snowman stanie na Field o type="Geyser" zostaje ona zraniona (hp Person się zmniejsza), jeśli type="Ice"- zostaje ona leczona
     */
    void fieldImpact(){
        if(this.type.equals(fieldTypes[0])) {
            if (Objects.equals(this.occupiedBy, "Hans")) {
                ((Turn) Main.turns.get(Main.turns.size() - 1)).hans.hp-= ((Turn) Main.turns.get(Main.turns.size() - 1)).hans.fieldReaction;
                if(((Turn) Main.turns.get(Main.turns.size() - 1)).hans.hp<1) {
                    Turn.notify("Hans caught his death of cold ["+this.x+","+this.y+"]!");
                    ((Turn) Main.turns.get(Main.turns.size() - 1)).kill(((Turn) Main.turns.get(Main.turns.size() - 1)).hans);
                }
            }
            if (Objects.equals(this.occupiedBy, "Anna")) {
                ((Turn) Main.turns.get(Main.turns.size() - 1)).anna.hp-= ((Turn) Main.turns.get(Main.turns.size() - 1)).anna.fieldReaction;
                if(((Turn) Main.turns.get(Main.turns.size() - 1)).anna.hp<1) {
                    Turn.notify("Anna froze to death ["+this.x+","+this.y+"]!");
                    ((Turn) Main.turns.get(Main.turns.size() - 1)).kill(((Turn) Main.turns.get(Main.turns.size() - 1)).anna);
                }
            }
            if (Objects.equals(this.occupiedBy, "Kristoff")) {
                ((Turn) Main.turns.get(Main.turns.size() - 1)).kristoff.hp-= ((Turn) Main.turns.get(Main.turns.size() - 1)).kristoff.fieldReaction;
                if(((Turn) Main.turns.get(Main.turns.size() - 1)).kristoff.hp<1) {
                    Turn.notify("Kristoff is frozen to the marrow ["+this.x+","+this.y+"]!");
                    ((Turn) Main.turns.get(Main.turns.size() - 1)).kill(((Turn) Main.turns.get(Main.turns.size() - 1)).kristoff);
                }
            }
            if (Objects.equals(this.occupiedBy, "Soldier")) {
                ((Soldier)((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(this.indexOfOccupiedBy) ).hp-= ((Soldier)((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(this.indexOfOccupiedBy) ).fieldReaction;
                if(((Soldier)((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(this.indexOfOccupiedBy) ).hp<1) {
                    Turn.notify("Soldier #" + ((Soldier)((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(this.indexOfOccupiedBy) ).id + " [" + ((Soldier)((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(this.indexOfOccupiedBy) ).x + "," + ((Soldier)((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(this.indexOfOccupiedBy) ).y + "] got to know what \"Frozen\" really means to his cost.");
                    ((Soldier)((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(this.indexOfOccupiedBy) ).die();

                }
            }
            if (Objects.equals(this.occupiedBy, "Snowman")) {
                ((Snowman)((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(this.indexOfOccupiedBy) ).hp+= ((Snowman)((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(this.indexOfOccupiedBy) ).fieldReaction;
            }
        }

        else if(this.type.equals(fieldTypes[3])) {
            if (Objects.equals(this.occupiedBy, "Hans")) {
                ((Turn) Main.turns.get(Main.turns.size() - 1)).hans.hp+= ((Turn) Main.turns.get(Main.turns.size() - 1)).hans.fieldReaction;
            }
            if (Objects.equals(this.occupiedBy, "Anna")) {
                ((Turn) Main.turns.get(Main.turns.size() - 1)).anna.hp+= ((Turn) Main.turns.get(Main.turns.size() - 1)).anna.fieldReaction;
            }
            if (Objects.equals(this.occupiedBy, "Kristoff")) {
                ((Turn) Main.turns.get(Main.turns.size() - 1)).kristoff.hp+= ((Turn) Main.turns.get(Main.turns.size() - 1)).kristoff.fieldReaction;
            }
            if (Objects.equals(this.occupiedBy, "Soldier")) {
                ((Soldier)((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(this.indexOfOccupiedBy) ).hp+= ((Soldier)((Turn) Main.turns.get(Main.turns.size() - 1)).soldiers.get(this.indexOfOccupiedBy) ).fieldReaction;
            }
            if (Objects.equals(this.occupiedBy, "Snowman")) {
                ((Snowman)((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(this.indexOfOccupiedBy) ).hp-= ((Snowman)((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(this.indexOfOccupiedBy) ).fieldReaction;
                if(((Snowman)((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(this.indexOfOccupiedBy) ).hp<1) {

                    Turn.notify("Snowman #" + ((Snowman)((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(this.indexOfOccupiedBy) ).id + " [" + ((Snowman)((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(this.indexOfOccupiedBy) ).x + "," + ((Snowman)((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(this.indexOfOccupiedBy) ).y + "] deliquesced.");
                    ((Snowman)((Turn) Main.turns.get(Main.turns.size() - 1)).snowmen.get(this.indexOfOccupiedBy) ).die();
                }
            }
        }
    }


}
