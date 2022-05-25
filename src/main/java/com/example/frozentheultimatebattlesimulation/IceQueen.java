package com.example.frozentheultimatebattlesimulation;
import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;

public class IceQueen extends Character{
    public IceQueen(int Hp, int MoveRange, int IceResistance) {
        super(Hp, 0, 0);
    }
    private void Freeze()
    {
       int x = (int)Math.floor(Math.random()*mapSize);
       int y = (int)Math.floor(Math.random()*mapSize);
       ((Turn) turns.get(turns.size()-1)).map[x][y].ChangeIntoIce();
    }
}
