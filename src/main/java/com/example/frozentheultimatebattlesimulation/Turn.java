package com.example.frozentheultimatebattlesimulation;


import java.util.ArrayList;
import java.util.Arrays;

public class Turn {
    Field[][] map;
    boolean isGameOver = false;

    IceQueen elsa;
    Person anna, kristoff, hans;
    ArrayList iceBreakers, snowmen, wolves, soldiers;




    Turn(){
        //generowanie pól na mapie
        this.map = new Field[Main.mapSize][Main.mapSize];
        for(int i =0; i< Main.mapSize; i++) for(int j = 0; j < Main.mapSize;j++) this.map[i][j] = new Field(j,i);
    }

    Turn(Turn original){
        this.map = new Field[Main.mapSize][Main.mapSize];
        for (int i = 0; i < Main.mapSize; i++) this.map[i] = original.map[i].clone(); // kopiowanie mapy

        try {
            this.elsa = (IceQueen) original.elsa.clone();
            this.anna = (Person) original.anna.clone();
            this.kristoff = (Person)original.kristoff.clone();
            this.hans = (Person)original.hans.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        this.iceBreakers = new ArrayList<IceBreaker>();
        original.iceBreakers.forEach((og) -> {
            try {
                this.iceBreakers.add(((IceBreaker)og).clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        });
        this.snowmen = new ArrayList<Snowman>();
        original.snowmen.forEach((og) -> {
            try {
                this.snowmen.add(((Snowman)og).clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        });

        this.soldiers = new ArrayList<Soldier>();
        original.soldiers.forEach((og) -> {
            try {
                this.soldiers.add(((Soldier)og).clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        });

        this.wolves = new ArrayList<Wolf>();
        original.wolves.forEach((og) -> {
            try {
                this.wolves.add(((Wolf)og).clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        });



    }

    void generateCharacters(){
        this.elsa = new IceQueen();
        this.anna = new Person(  1,  false, "Anna");
        this.kristoff = new Person(  1,  true, "Kristoff");
        this.hans = new Person( 1,  false, "Hans");

        this.iceBreakers = new ArrayList<IceBreaker>();
        for(int i =0; i<Math.floor(0.04*Math.pow(Main.mapSize, 2)); i++) this.iceBreakers.add(new IceBreaker());

        this.snowmen = new ArrayList<Snowman>();
        for(int i =0; i<Main.elsasArmySize; i++) this.snowmen.add(new Snowman());

        this.soldiers = new ArrayList<Soldier>();
        for(int i =0; i<Main.hansArmySize; i++) this.soldiers.add(new Soldier());

        this.wolves = new ArrayList<Wolf>();
        for(int i =0; i<Math.floor(0.04*Math.pow(Main.mapSize, 2)); i++) this.wolves.add(new Wolf());
    }

    }
