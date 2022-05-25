package com.example.frozentheultimatebattlesimulation;

public class IceBreaker extends Character{
    public static int IceBreakersQuantity; //ilosc lamaczy lodu liczona na podstawie podanej przez uzytkownika mapSize
    private static int IceBreakersCounter=0; //aktulana ilosc lamaczy lodu
    public IceBreaker(int Hp, int MoveRange, int IceResistance) {
        super(Hp, MoveRange, IceResistance);
        IceBreakersCounter++;
    }

}
