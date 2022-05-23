package com.example.frozentheultimatebattlesimulation;


import static com.example.frozentheultimatebattlesimulation.Main.mapSize;

public class Character extends Element {
    protected int Hp;
    protected int MoveRange;
    public int Strength;
    protected int IceResistance;

    public Character(int Hp, int MoveRange, int IceResistance)
    {
        this.Hp=Hp;
        this.MoveRange=MoveRange;
        this.IceResistance=IceResistance;
        int x = (int)Math.floor(Math.random()*mapSize);
        int y = (int)Math.floor(Math.random()*mapSize);
      //  if(!Mapa[x][y].isEmpty)
        SetCoordinates(x,y);

    }

    protected void Move()
    {
        if(MoveRange!=0){
            String[] kierunki ={"Lewy", "Prawy", "Gora", "Dol"};
            int random_int= (int)Math.floor(Math.random()*4);
            String kierunek=kierunki[random_int];
            X= switch(kierunek)
                    {
                        case "Lewy": yield X=(X-MoveRange)%mapSize;
                        case "Prawy": yield X=(X+MoveRange)%mapSize;
                        default: yield X;
                    };
            Y= switch(kierunek)
                    {
                        case "Gora": yield Y=(Y+MoveRange)%mapSize;
                        case "Dol": yield Y=(Y-MoveRange)%mapSize;
                        default: yield Y;
                    };
        }

    }

    protected void IceReaction()
    {
        Hp-=IceResistance;
    }

}
