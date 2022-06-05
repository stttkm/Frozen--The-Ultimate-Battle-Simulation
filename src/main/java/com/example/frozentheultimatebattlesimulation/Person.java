package com.example.frozentheultimatebattlesimulation;

import javafx.scene.image.Image;
import javafx.stage.Screen;
import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;

/**
 * Klasa Person dziedziczy po klasie Attacker
 * Jej przedstawicielami są: Anna, Hans i Kristoff
 */

public class Person extends Attacker{
    boolean hasHorse;
    String name;

    /**
     * Class Constructor
     */
    public Person()
    {
        super();
        hasHorse=false;
    }

    /**
     * Specifying Class Constructor
     * @param hasHorse określa czy Person ma konia (posidaanie konia przekłada się na MoveRange tego obiektu)
     * @param whothis Imię tworzonego obiektu Person, parametr pozwala na dobór zdjęcia do ikonki na wyświetlanej mapce symulacji.
     */
    public Person( boolean hasHorse, String whothis) {
        super(2*mapSize,1, 1, 1);
        this.hasHorse=hasHorse;
        if(hasHorse) this.MoveRange=MoveRange+1;
        ((Turn) turns.get(0)).map[y][x].occupiedBy=whothis;
        name = whothis;
        characterImage = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/" + whothis +".png", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true);
    }
}
