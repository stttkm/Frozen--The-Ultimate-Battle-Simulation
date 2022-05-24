package com.example.frozentheultimatebattlesimulation;

public class Element {
    protected int X;
    protected int Y;

    protected Element()
    {
        X=0; Y=0;
    }

    protected void SetCoordinates(int x, int y)
    {
        this.X=x;
        this.Y=y;
    }

}
