package com.example.frozentheultimatebattlesimulation;

import java.util.Random;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;

public class IceBreaker extends Character{
    public static int iceBreakersQuantity; //ilosc lamaczy lodu liczona na podstawie podanej przez uzytkownika mapSize
    private static int iceBreakersCounter=0; //aktulana ilosc lamaczy lodu
    public IceBreaker(int Hp, int MoveRange, int IceResistance) {
        super(Hp, MoveRange, IceResistance);
        iceBreakersCounter++;}

    public IceBreaker() {
        super();
            iceBreakersCounter++;
    }

    protected void CrashIce()
    {
            String[] directions ={"Left", "Right", "Top", "Bottom"};
            int random_int= (int)Math.floor(Math.random()*4);
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
