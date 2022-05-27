package com.example.frozentheultimatebattlesimulation;

import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;

public class IceBreaker extends Character{

    public IceBreaker() {
        super();
    }

    protected void CrashIce()
    {
            String[] directions ={"Left", "Right", "Top", "Bottom"};
            String direction;
            do
            {
                Random random = new Random();
                direction=directions[random.nextInt(4)];
                this.x= switch(direction)
                        {
                            case "Left":
                                yield (((this.x-MoveRange)+mapSize)%mapSize);
                            case "Right":
                                yield (((this.x+MoveRange)+mapSize)%mapSize);
                            default: yield this.x;
                        };
                this.y= switch(direction)
                        {
                            case "Top": yield (((this.y+MoveRange)+mapSize)%mapSize);
                            case "Bottom": yield (((this.y-MoveRange)+mapSize)%mapSize);
                            default: yield this.y;
                        };
            }while(!((Turn)Main.turns.get(Main.turns.size()-1)).map[y][x].type.equals("Ice"));
            ((Turn)Main.turns.get(Main.turns.size()-1)).map[y][x].ChangeIntoWater();

        }

}
