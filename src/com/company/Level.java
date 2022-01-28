package com.company;

public class Level {
    int SL;
    double szansa;

    public Level(int SL, double szansa) {
        this.SL = SL;
        this.szansa = szansa;
    }

    public Level(double szansa, int SL) {
        this.SL = SL;
        this.szansa = szansa;
    }

    void setSzansa(double sza){
        szansa=sza;
    }
    void setSL(int S){
        SL=S;
    }
    void set(double sza,int S){
        szansa=sza;
        SL=S;
    }
    void addSzansa(double sza){
        szansa=szansa+sza;
    }


}
