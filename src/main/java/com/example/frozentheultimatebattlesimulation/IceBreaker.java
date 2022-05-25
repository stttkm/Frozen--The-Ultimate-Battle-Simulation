package com.example.frozentheultimatebattlesimulation;

import static com.example.frozentheultimatebattlesimulation.Main.Mapa;
import static com.example.frozentheultimatebattlesimulation.Main.mapSize;

public class IceBreaker extends Character{
    public static int Quantity; //ilość łamaczy lodu liczona na podstawie podanej przez użytkownika wielkości mapSize
    private static int counter=0; //aktulana ilosc lamaczy lodu
    public IceBreaker() {
        super();
        counter++;
    }

    protected void CrashIce()
    {
            String[] kierunki ={"Lewy", "Prawy", "Gora", "Dol"};
            int random_int= (int)Math.floor(Math.random()*4);
            String kierunek;
            int x=X;
            int y=Y;
            do
            {
                kierunek=kierunki[random_int];
                x= switch(kierunek)
                        {
                            case "Lewy": yield x=(x-MoveRange)%mapSize;
                            case "Prawy": yield x=(x+MoveRange)%mapSize;
                            default: yield x;
                        };
                y= switch(kierunek)
                        {
                            case "Gora": yield y=(y+MoveRange)%mapSize;
                            case "Dol": yield y=(y-MoveRange)%mapSize;
                            default: yield y;
                        };
            }while(!Mapa[x][y].type.equals("Ice"));
            Mapa[x][y].type="Water";

        }

}
