package com.example.frozentheultimatebattlesimulation;

/**
 * Klasa, po której dziedziczy większość klas używanych w symulacji
 * Ma dwa pola x i y oznaczające współrzędne
 */
public class Element {
    protected int x;
    protected int y;

    /**
     * Class Constructor
     */
    protected Element()
    {
        x =0;
        y =0;
    }

    /**
     * Metoda ustawiająca współrzędne elementu
     * @param x oznacza współrzędną x, którą chcemy ustawić
     * @param y oznacza współrzędną y, którą chcemy ustawić
     */
    protected void setCoordinates(int x, int y)
    {
        this.x =x;
        this.y =y;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
            return super.clone();
    }

}
