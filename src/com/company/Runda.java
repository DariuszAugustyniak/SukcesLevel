package com.company;

import java.util.ArrayList;

public class Runda {

    ArrayList<Level> runda;

    public Runda() {
        this.runda = new ArrayList<Level>();
    }


    public void printAll(){
        for (Level level: runda) {
            System.out.println("SL :"+ level.SL + "  Szansa :" + level.szansa);
        }
    }

    public Level ZnajdzLevel(int SL){
        for (Level level: runda) {
            if(level.SL==SL){
                return level;
            }
        }
        return new Level(SL,0D);
    }
    public Level DodajLevel(Level Dodawany){
        for (Level level: runda) {
            if(level.SL==Dodawany.SL){
                level.addSzansa(Dodawany.szansa);
                return level;
            }
        }
        runda.add(Dodawany);
        return Dodawany;
    }


    public Runda iteracja(Runda poprzednia){
        Runda nowa = new Runda();
        for (Level level:poprzednia.runda) {
            for (Level l:poprzednia.runda) {
                Level nowy = new Level(level.SL+l.SL,level.szansa*l.szansa);
                nowa.DodajLevel(nowy);
            }
        }

        return nowa;
    }

    public void pierwsza(){
        this.runda.add(0, new Level(0.08,-3));
        this.runda.add(0, new Level(0.08,-2));
        this.runda.add(0, new Level(0.08,-1));
        this.runda.add(0, new Level(0.08,0));
        this.runda.add(0, new Level(0.01,1));
        this.runda.add(0, new Level(0.09,2));
        this.runda.add(0, new Level(0.09,3));
        this.runda.add(0, new Level(0.09,4));
        this.runda.add(0, new Level(0.09,5));
        this.runda.add(0, new Level(0.09,6));
        this.runda.add(0, new Level(0.09,7));
      /*
        this.runda.get(0).set(0.08,-3);
        this.runda.get(1).set(0.08,-2);
        this.runda.get(2).set(0.08,-1);
        this.runda.get(3).set(0.08,0);
        this.runda.get(4).set(0.01,1);
        this.runda.get(5).set(0.09,2);
        this.runda.get(6).set(0.09,3);
        this.runda.get(7).set(0.09,4);
        this.runda.get(8).set(0.09,5);
        this.runda.get(9).set(0.09,6);
        this.runda.get(10).set(0.09,7);

*/



    }
}

