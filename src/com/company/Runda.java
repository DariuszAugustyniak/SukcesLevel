package com.company;

import java.math.BigDecimal;
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
    public void printChanceLagrerThan(int OczekiwanySL){
        BigDecimal wynik = BigDecimal.valueOf(0);
        for (Level level: runda) {

            if(level.SL>=OczekiwanySL){
                wynik=wynik.add(level.szansa);
            }

        }
        System.out.println("Sznasa na osiągnięce conajmniej "+OczekiwanySL+" wynosi:" + wynik);
    }


    public Level ZnajdzLevel(int SL){
        for (Level level: runda) {
            if(level.SL==SL){
                return level;
            }
        }
        return new Level(SL,BigDecimal.valueOf(0));
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
                Level nowy = new Level(level.SL+l.SL,level.szansa.multiply(l.szansa));
                nowa.DodajLevel(nowy);
            }
        }

        return nowa;
    }

    public void pierwsza(){
        this.runda.add(0, new Level(new BigDecimal("0.08"),-3));
        this.runda.add(0, new Level(new BigDecimal("0.08"),-2));
        this.runda.add(0, new Level(new BigDecimal("0.08"),-1));
        this.runda.add(0, new Level(new BigDecimal("0.08"),0));
        this.runda.add(0, new Level(new BigDecimal("0.01"),1));
        this.runda.add(0, new Level(new BigDecimal("0.09"),2));
        this.runda.add(0, new Level(new BigDecimal("0.09"),3));
        this.runda.add(0, new Level(new BigDecimal("0.09"),4));
        this.runda.add(0, new Level(new BigDecimal("0.09"),5));
        this.runda.add(0, new Level(new BigDecimal("0.09"),6));
        this.runda.add(0, new Level(new BigDecimal("0.09"),7));




    }
}

