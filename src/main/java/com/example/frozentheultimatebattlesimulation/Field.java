package com.example.frozentheultimatebattlesimulation;

public class Field extends Element
{
    static String types[]= {"Ice", "Water", "Earth", "Geyser"};
    String type;
    boolean isEmpty;
    protected Field(int x, int y)
    {
        SetCoordinates(x,y);
        type=types[0];
        isEmpty=true;
    }
    public void ChangeIntoIce()
    {
        if(type==types[1]) type=types[0];
    }
    public void ChangeIntoWater()
    {
        if(type==types[0]) type=types[1];
    }
}
