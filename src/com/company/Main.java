package com.company;

public class Main {

    public static void main(String[] args) {
     /*   Runda zero = new Runda();
        zero.pierwsza();
        zero.printAll();
        Runda jeden = zero.iteracja(zero);
        jeden.printAll();
       // jeden.printChanceLagrerThan(4);
        Runda dwa = jeden.iteracja(jeden);
        dwa.printChanceLagrerThan(20);
        dwa.printAll();
        Runda trzy = dwa.iteracja(dwa);
        trzy.printChanceLagrerThan(20);
        trzy.printAll();
        Runda cztery = trzy.iteracja(trzy);

        cztery.printChanceLagrerThan(20);
        cztery.printAll();/*/
	// write your code here

        WarstwaSymulacji pierwsza = new WarstwaSymulacji();
        pierwsza.wypełnijPoRówno();
        pierwsza.wypełnijPoziomSukcesu(60);
        pierwsza.podajSLWiekszeOd(3);
        pierwsza.wypełnijEfektyiPS(60,1);
        pierwsza.wypisz();
        WarstwaSymulacji druga = pierwsza.dodajKolejnąWarstwę(60,1);
        druga.wypisz();
        druga.wyspiszSzanse();
       /* WarstwaSymulacji druga = pierwsza.dodajNowąWarstwę();
        druga.wypisz();
        druga.podajSLWiekszeOdProcenty(6);
        WarstwaSymulacji trzecia =druga.dodajNowąWarstwę(pierwsza);
        trzecia.wypisz();
        trzecia.podajSLWiekszeOdProcenty(6);
        WarstwaSymulacji czwarta =trzecia.dodajNowąWarstwę(pierwsza);
        czwarta.wypisz();
        czwarta.podajSLWiekszeOdProcenty(6);*/

    }
}
