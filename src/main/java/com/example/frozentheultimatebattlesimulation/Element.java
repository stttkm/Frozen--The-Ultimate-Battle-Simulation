package com.example.frozentheultimatebattlesimulation;

public class Element {
    protected int x;
    protected int y;

    protected Element()
    {
        x =0; y =0;
    }

    protected void SetCoordinates(int x, int y)
    {
        this.x =x;
        this.y =y;
    }

}
