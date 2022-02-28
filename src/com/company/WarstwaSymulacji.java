package com.company;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WarstwaSymulacji {

    Map<Integer, Integer> warstwa;
    Map<Integer, Integer> poziomySukcesu;
    Map<String, Integer> efektyPS;

    public WarstwaSymulacji() {
    warstwa = new HashMap<>();
    poziomySukcesu = new HashMap<>();
    efektyPS = new HashMap<>();

    }

    public WarstwaSymulacji(Map<Integer, Integer> warstwa) {
        this.warstwa = warstwa;
    }


    void wypełnijPoRówno(){
         for(int i=1;i<101;i++){
             warstwa.put(i,100);
         }
     }
    void wypełnijPoziomSukcesu(int wartość){
        for(int i=0; i<warstwa.size();i++){

            int wartośćrzutu=i+1;

            int SL=(wartość-wartośćrzutu)/10;

            if(poziomySukcesu.containsKey(SL)){
            int doWpisania= poziomySukcesu.get(SL)+warstwa.get(i+1);
                poziomySukcesu.put(SL,doWpisania);
            }else {
                poziomySukcesu.put(SL,warstwa.get(i+1));
            }


        }
    }
    void wypełnijEfektyiPS(int wartość, int talent){
        for (Integer klucz:warstwa.keySet()) {
            Integer Sl=(wartość-klucz)/10;
            if(klucz<=wartość){//sukces
                if((klucz/10==klucz%10)||(klucz<=5)){//krytyczny sukces
                    System.out.println("krytycznySukces" + klucz);
                    if (efektyPS.containsKey("krytycznySukces")){
                        efektyPS.put("krytycznySukces",efektyPS.get("krytycznySukces")+warstwa.get(klucz));

                    }else {
                        efektyPS.put("krytycznySukces",warstwa.get(klucz));

                    }
                }
                else{
                    System.out.println("Sukces" + klucz);
                    //zwykły sukces
                    Sl=Sl+talent;
                    if (efektyPS.containsKey(Sl.toString())) {
                        efektyPS.put(Sl.toString(), efektyPS.get(Sl.toString()) + warstwa.get(klucz));
                    }else {
                        efektyPS.put(Sl.toString(),warstwa.get(klucz));
                    }
                }
            }
            else{//porażka
                if((klucz/10==klucz%10)||(klucz>=96)){
                    //krytyczna porażka
                    System.out.println("Krytyczna Porażka" + klucz);
                    if (efektyPS.containsKey("krytycznaPorażka")){
                        efektyPS.put("krytycznaPorażka",efektyPS.get("krytycznaPorażka")+warstwa.get(klucz));
                    }else {
                        efektyPS.put("krytycznaPorażka",warstwa.get(klucz));
                    }
                }
                else {
                    //zwykła porażka
                    System.out.println("porażka" + klucz);
                    if (efektyPS.containsKey(Sl.toString())) {
                        efektyPS.put(Sl.toString(), efektyPS.get(Sl.toString()) + warstwa.get(klucz));
                    }else {
                        efektyPS.put(Sl.toString(),warstwa.get(klucz));
                    }
                }
                }
            }

        }
    void wypiszSzanse(){
        for (String klucz: efektyPS.keySet()) {

        }
        System.out.println(warstwa);
        System.out.println(poziomySukcesu);
        System.out.println(efektyPS);
    }


    void wypisz(){
        System.out.println(warstwa);
        System.out.println(poziomySukcesu);
        System.out.println(efektyPS);
    }
    void podajSLWiekszeOd(int cel){
        int wynik=0;
        int zapasowycel = cel;
        while(poziomySukcesu.containsKey(cel)){

            wynik=wynik+poziomySukcesu.get(cel);
            cel++;

        }
        System.out.println("cel to"+ zapasowycel +"wynik "+wynik);
    }


    WarstwaSymulacji dodajNowąWarstwę(){
        WarstwaSymulacji nowa = new WarstwaSymulacji();
        nowa.wypełnijPoRówno();

        for (Integer pierwszyKlucz:this.poziomySukcesu.keySet()) {
            for (Integer drugiKlucz:this.poziomySukcesu.keySet()) {
                Integer nowyKlucz=pierwszyKlucz+ drugiKlucz;
                if(nowa.poziomySukcesu.containsKey(nowyKlucz)){
                    Integer najnowszaWartosć = nowa.poziomySukcesu.get(nowyKlucz)+poziomySukcesu.get(pierwszyKlucz)+poziomySukcesu.get(drugiKlucz);
                    nowa.poziomySukcesu.put(nowyKlucz,najnowszaWartosć);
                }else{
                    Integer najnowszaWartosć = poziomySukcesu.get(pierwszyKlucz)+poziomySukcesu.get(drugiKlucz);
                    nowa.poziomySukcesu.put(nowyKlucz,najnowszaWartosć);
                }
            }
        }
    return nowa;


    }
    WarstwaSymulacji dodajNowąWarstwę(WarstwaSymulacji dodatkowa){
        WarstwaSymulacji nowa = new WarstwaSymulacji();
        nowa.wypełnijPoRówno();

        for (Integer pierwszyKlucz:this.poziomySukcesu.keySet()) {
            for (Integer drugiKlucz:dodatkowa.poziomySukcesu.keySet()) {
                Integer nowyKlucz=pierwszyKlucz+ drugiKlucz;
                if(nowa.poziomySukcesu.containsKey(nowyKlucz)){
                    Integer najnowszaWartosć = nowa.poziomySukcesu.get(nowyKlucz)+poziomySukcesu.get(pierwszyKlucz)+dodatkowa.poziomySukcesu.get(drugiKlucz);
                    nowa.poziomySukcesu.put(nowyKlucz,najnowszaWartosć);
                }else{
                    Integer najnowszaWartosć = poziomySukcesu.get(pierwszyKlucz)+dodatkowa.poziomySukcesu.get(drugiKlucz);
                    nowa.poziomySukcesu.put(nowyKlucz,najnowszaWartosć);
                }
            }
        }
        return nowa;


    }
    Integer wszystkieRzuty(){
        Integer wynik =0;
        for (Integer klucz:poziomySukcesu.keySet()) {
            wynik= wynik + poziomySukcesu.get(klucz);
        }


        return wynik;
    }

    void podajSLWiekszeOdProcenty(int cel){
        Integer wynik=0;
        int zapasowycel = cel;
        while(poziomySukcesu.containsKey(cel)){

            wynik=wynik+poziomySukcesu.get(cel);
            cel++;

        }
        System.out.println(wynik +" /  "+this.wszystkieRzuty());
        double ułamek=wynik.doubleValue()/this.wszystkieRzuty().doubleValue();
        System.out.println("cel to"+ zapasowycel +"wynik "+ułamek);
    }



}
