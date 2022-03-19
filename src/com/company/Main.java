package com.company;

public class Main {

    public static void main(String[] args) {


        WarstwaSymulacji pierwsza = new WarstwaSymulacji();
        pierwsza.wypełnijPoRówno();
        pierwsza.wypełnijPoziomSukcesu(60);
        pierwsza.podajSLWiekszeOd(3);
        pierwsza.wypełnijEfektyiPS(60,1);
        pierwsza.wypisz();
        WarstwaSymulacji druga = pierwsza.dodajKolejnąWarstwę(60,1);
        druga.wypisz();
        druga.wyspiszSzanse();
        druga.szansaNaConajmniej(7);


    }
}
