package com.example.frozentheultimatebattlesimulation;

import static com.example.frozentheultimatebattlesimulation.Main.Mapa;
import static com.example.frozentheultimatebattlesimulation.Main.mapSize;

public class IceQueen extends Character{
    public IceQueen(int Hp, int MoveRange, int IceResistance) {
        super(Hp, 0, 0);
    }
    private void Freeze()
    {
       int x = (int)Math.floor(Math.random()*mapSize);
       int y = (int)Math.floor(Math.random()*mapSize);
       Mapa[x][y].ChangeIntoIce();
    }
}
