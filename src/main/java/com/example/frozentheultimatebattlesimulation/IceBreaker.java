package com.example.frozentheultimatebattlesimulation;

public class IceBreaker extends Character{
    public static int Quantity; //ilosc lamaczy lodu liczona na podstawie podanej przez uzytkownika mapSize
    private static int counter=0; //aktulana ilosc lamaczy lodu
    public IceBreaker(int Hp, int MoveRange, int IceResistance) {
        super(Hp, MoveRange, IceResistance);
        counter++;
    }

}
