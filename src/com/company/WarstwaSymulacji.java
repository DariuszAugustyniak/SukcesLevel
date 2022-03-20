package com.company;


import java.util.HashMap;
import java.util.Map;


public class WarstwaSymulacji {

    Map<Integer, Integer> warstwa = new HashMap<>();
    Map<Integer, Integer> poziomySukcesu = new HashMap<>();
    Map<String, Integer> efektyPS = new HashMap<>();


    void wypełnijPoRówno() {
        for (int i = 1; i < 101; i++) {
            warstwa.put(i, 100);
        }
    }

    void wypełnijPoziomSukcesu(int wartość) {
        for (int i = 0; i < warstwa.size(); i++) {

            int wartośćrzutu = i + 1;

            int SL = (wartość - wartośćrzutu) / 10;


            poziomySukcesu.merge(SL, warstwa.get(i + 1), (a, b) -> a + b);


        }
    }


    void wypełnijEfektyiPS(int wartość, int talent) {
        for (Integer klucz : warstwa.keySet()) {
            Integer Sl = (wartość - klucz) / 10;
            if (klucz <= wartość) {//sukces
                if ((klucz / 10 == klucz % 10) || (klucz <= 5)) {//krytyczny sukces
                    System.out.println("krytycznySukces" + klucz);
                    efektyPS.merge("krytycznySukces", warstwa.get(klucz), (a, b) -> a + b);

                } else {
                    System.out.println("Sukces" + klucz);
                    //zwykły sukces
                    Sl = Sl + talent;
                    efektyPS.merge(Sl.toString(), warstwa.get(klucz), (a, b) -> a + b);

                }
            } else {//porażka
                if ((klucz / 10 == klucz % 10) || (klucz >= 96)) {
                    //krytyczna porażka
                    System.out.println("Krytyczna Porażka" + klucz);
                    efektyPS.merge("krytycznaPorażka", warstwa.get(klucz), (a, b) -> a + b);

                } else {
                    //zwykła porażka
                    System.out.println("zwykłaPorażka" + klucz);
                    efektyPS.merge("zwykłaPorażka", warstwa.get(klucz), (a, b) -> a + b);

                }
            }
        }
    }


    void wypiszSzanse() {
        for (String klucz : efektyPS.keySet()) {

        }
        System.out.println(warstwa);
        System.out.println(poziomySukcesu);
        System.out.println(efektyPS);
    }


    void wypisz() {
        System.out.println(warstwa);
        System.out.println(poziomySukcesu);
        System.out.println(efektyPS);
    }

    void podajSLWiekszeOd(int cel) {
        int wynik = 0;
        int zapasowycel = cel;
        while (poziomySukcesu.containsKey(cel)) {

            wynik = wynik + poziomySukcesu.get(cel);
            cel++;

        }
        System.out.println("cel to" + zapasowycel + "wynik " + wynik);
    }


    WarstwaSymulacji dodajNowąWarstwę() {
        WarstwaSymulacji nowa = new WarstwaSymulacji();
        nowa.wypełnijPoRówno();

        for (Integer pierwszyKlucz : this.poziomySukcesu.keySet()) {
            for (Integer drugiKlucz : this.poziomySukcesu.keySet()) {
                Integer nowyKlucz = pierwszyKlucz + drugiKlucz;
                nowa.poziomySukcesu.merge(nowyKlucz, poziomySukcesu.get(pierwszyKlucz) + poziomySukcesu.get(drugiKlucz), (a, b) -> a + b);

            }
        }
        return nowa;
    }

    WarstwaSymulacji dodajKolejnąWarstwę(int szansa, int talent) {
        WarstwaSymulacji nowa = new WarstwaSymulacji();
        nowa.wypełnijPoRówno();
        nowa.wypełnijPoziomSukcesu(szansa);
        nowa.efektyPS.put("krytycznySukces", this.efektyPS.get("krytycznySukces"));
        nowa.efektyPS.put("krytycznaPorażka", this.efektyPS.get("krytycznaPorażka"));
        nowa.efektyPS.put("zwykłaPorażka", this.efektyPS.get("zwykłaPorażka"));
        for (String klucz : efektyPS.keySet()) {
            if (klucz.equals("krytycznySukces") || klucz.equals("krytycznaPorażka") || klucz.equals("zwykłaPorażka")) {

            } else {
                Integer starySL = Integer.parseInt(klucz);
                for (Integer wynik : warstwa.keySet()) {
                    Integer nowySL = (szansa - wynik) / 10;
                    Integer suma = nowySL + starySL;
                    System.out.println("suma to" + suma + " wynik to " + wynik);
                    if (wynik / 10 == wynik % 10 || wynik <= 5 || wynik > 95) {//jakiś krytyk
                        if (wynik <= szansa) {//krytyczny sukses
                            nowa.efektyPS.put("krytycznySukces", nowa.efektyPS.get("krytycznySukces") + warstwa.get(wynik));

                        } else {//krytyczna porażka
                            nowa.efektyPS.put("krytycznaPorażka", nowa.efektyPS.get("krytycznaPorażka") + warstwa.get(wynik));

                        }
                    } else {//nie krytyk
                        if (suma.intValue() > 0) {//sukces
                            if (nowa.efektyPS.containsKey(suma.toString())) {
                                nowa.efektyPS.put(suma.toString(), nowa.efektyPS.get(suma.toString()) + warstwa.get(wynik));
                            } else {
                                nowa.efektyPS.put(suma.toString(), warstwa.get(wynik));
                            }
                        } else {//porażka
                            nowa.efektyPS.put("zwykłaPorażka", nowa.efektyPS.get("zwykłaPorażka") + warstwa.get(wynik));
                        }
                    }


                }


            }
        }


        return nowa;
    }

    Integer SumaEfektów() {
        Integer suma = 0;
        for (String klucz : efektyPS.keySet()) {
            suma = suma + efektyPS.get(klucz);
        }
        return suma;
    }

    void wyspiszSzanse() {
        Integer suma = SumaEfektów();
        Double sumaProcentów = 0.0;
        for (String klucz : efektyPS.keySet()) {
            Double procent = efektyPS.get(klucz).doubleValue() / suma.doubleValue();
            sumaProcentów = sumaProcentów + procent;
            System.out.println("szansa na " + klucz + " to " + procent + "%");
        }
        System.out.println("w sumie " + sumaProcentów + "%");
    }

    void szansaNaConajmniej(int poziom) {
        Integer suma = SumaEfektów();
        Integer sumasukcesów = 0;
        Integer sumaniedostatecznych = 0;
        for (String klucz : efektyPS.keySet()) {
            if (klucz.equals("krytycznaPorażka") || klucz.equals("krytycznySukces") || klucz.equals("zwykłaPorażka")) {
                Double procent = efektyPS.get(klucz).doubleValue() / suma.doubleValue();
                System.out.println("szansa na " + klucz + " to " + procent + "%");
            } else {
                if (Integer.parseInt(klucz) >= poziom) {
                    sumasukcesów = sumasukcesów + efektyPS.get(klucz);
                } else {
                    sumaniedostatecznych = sumaniedostatecznych + efektyPS.get(klucz);
                }
            }

        }
        Double procent = sumasukcesów.doubleValue() / suma.doubleValue();
        Double procent2 = sumaniedostatecznych.doubleValue() / suma.doubleValue();
        System.out.println("szansa na wiecej niż " + poziom + " to " + procent + "%");
        System.out.println("szansa kontynuację dalej to " + procent2 + "%");
    }

    WarstwaSymulacji dodajNowąWarstwę(WarstwaSymulacji dodatkowa) {
        WarstwaSymulacji nowa = new WarstwaSymulacji();
        nowa.wypełnijPoRówno();

        for (Integer pierwszyKlucz : this.poziomySukcesu.keySet()) {
            for (Integer drugiKlucz : dodatkowa.poziomySukcesu.keySet()) {
                Integer nowyKlucz = pierwszyKlucz + drugiKlucz;
                if (nowa.poziomySukcesu.containsKey(nowyKlucz)) {
                    Integer najnowszaWartosć = nowa.poziomySukcesu.get(nowyKlucz) + poziomySukcesu.get(pierwszyKlucz) + dodatkowa.poziomySukcesu.get(drugiKlucz);
                    nowa.poziomySukcesu.put(nowyKlucz, najnowszaWartosć);
                } else {
                    Integer najnowszaWartosć = poziomySukcesu.get(pierwszyKlucz) + dodatkowa.poziomySukcesu.get(drugiKlucz);
                    nowa.poziomySukcesu.put(nowyKlucz, najnowszaWartosć);
                }
            }
        }
        return nowa;


    }

    Integer wszystkieRzuty() {
        Integer wynik = 0;
        for (Integer klucz : poziomySukcesu.keySet()) {
            wynik = wynik + poziomySukcesu.get(klucz);
        }


        return wynik;
    }

    void podajSLWiekszeOdProcenty(int cel) {
        Integer wynik = 0;
        int zapasowycel = cel;
        while (poziomySukcesu.containsKey(cel)) {

            wynik = wynik + poziomySukcesu.get(cel);
            cel++;

        }
        System.out.println(wynik + " /  " + this.wszystkieRzuty());
        double ułamek = wynik.doubleValue() / this.wszystkieRzuty().doubleValue();
        System.out.println("cel to" + zapasowycel + "wynik " + ułamek);
    }


}
